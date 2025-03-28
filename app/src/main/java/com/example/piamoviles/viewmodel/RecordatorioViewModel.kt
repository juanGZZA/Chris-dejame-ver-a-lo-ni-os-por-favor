package com.example.piamoviles.viewmodel

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.piamoviles.model.Recordatorio
import com.example.piamoviles.repository.RecordatorioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.piamoviles.receiver.ReminderReceiver
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId


class RecordatorioViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RecordatorioRepository(application.applicationContext)
    private val _recordatorios = MutableStateFlow<List<Recordatorio>>(emptyList())
    @SuppressLint("StaticFieldLeak")
    private val context: Context = application.applicationContext
    val recordatorios: StateFlow<List<Recordatorio>> = _recordatorios.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun crearRecordatorio(recordatorio: Recordatorio, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            repository.crearRecordatorio(recordatorio) { success ->
                if (success) {
                    programarAlarma(recordatorio)
                    fetchRecordatorios()
                }
                onComplete(success)
            }
        }
    }

    @SuppressLint("ScheduleExactAlarm")
    @RequiresApi(Build.VERSION_CODES.O)
    private fun programarAlarma(recordatorio: Recordatorio) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, ReminderReceiver::class.java).apply {
            putExtra("title", recordatorio.titulo)
            putExtra("message", recordatorio.notas)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            recordatorio.id.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val fechaCompleta = LocalDateTime.of(
            LocalDate.parse(recordatorio.fecha),
            LocalTime.parse(recordatorio.hora)
        )
        val alarmTime = fechaCompleta.minusDays(1)
            .atZone(ZoneId.systemDefault())
            .toInstant()
            .toEpochMilli()

        alarmManager.setExact(
            AlarmManager.RTC_WAKEUP,
            alarmTime,
            pendingIntent
        )
    }

    fun fetchRecordatorios() {
        viewModelScope.launch {
            repository.obtenerRecordatorios { recordatorios ->
                recordatorios?.let {
                    _recordatorios.value = it
                }
            }
        }
    }

    fun eliminarRecordatorio(id: Int) {
        viewModelScope.launch {
            repository.eliminarRecordatorio(id) { success ->
                if (success) {
                    fetchRecordatorios()
                }
            }
        }
    }
}