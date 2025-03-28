package com.example.piamoviles.pantallas

import android.app.TimePickerDialog
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.piamoviles.model.Recordatorio
import com.example.piamoviles.viewmodel.RecordatorioViewModel
import java.time.LocalDate
import java.time.LocalTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AgregarRecordatorioScreen(navController: NavController, mascotaId: Int) {
    val viewModel: RecordatorioViewModel = viewModel()
    var titulo by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf<LocalDate?>(null) }
    var hora by remember { mutableStateOf<LocalTime?>(null) }
    var notas by remember { mutableStateOf("") }
    var showDatePicker by remember { mutableStateOf(false) }
    var showTimePicker by remember { mutableStateOf(false) }

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            onDateSelected = { date ->
                fecha = date
                showDatePicker = false
            }
        )
    }

    if (showTimePicker) {
        TimePickerDialog(
            onDismissRequest = { showTimePicker = false },
            onTimeSelected = { time ->
                hora = time
                showTimePicker = false
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Nuevo Recordatorio", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { showDatePicker = true },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(fecha?.toString() ?: "Seleccionar fecha")
        }

        Button(
            onClick = { showTimePicker = true },
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
        ) {
            Text(hora?.toString() ?: "Seleccionar hora")
        }

        OutlinedTextField(
            value = notas,
            onValueChange = { notas = it },
            label = { Text("Notas adicionales") },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(top = 8.dp),
            maxLines = 3
        )

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                if (fecha != null && hora != null) {
                    val recordatorio = Recordatorio(
                        mascota_id = mascotaId,
                        titulo = titulo,
                        fecha = fecha!!.toString(),
                        hora = hora!!.toString(),
                        notas = notas // Corregido de 'notes' a 'notas'
                    )
                    viewModel.crearRecordatorio(recordatorio) { success ->
                        if (success) navController.popBackStack()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Recordatorio")
        }
    }
}