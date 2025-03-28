package com.example.piamoviles.model
data class Mascota(
    val id: Int,
    val nombre: String,
    val edad: Int,
    val informacion: String,
    val habitos: String,
    val salud: String,
    val notas: String,
    val img: String? = null
)