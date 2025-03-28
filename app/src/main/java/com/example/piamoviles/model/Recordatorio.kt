package com.example.piamoviles.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

data class Recordatorio @RequiresApi(Build.VERSION_CODES.O) constructor(
    val id: Int = 0,
    val mascota_id: Int,
    val titulo: String,
    val fecha: String,
    val hora: String,
    val notas: String,
    val creado_en: String = LocalDateTime.now().toString()
)