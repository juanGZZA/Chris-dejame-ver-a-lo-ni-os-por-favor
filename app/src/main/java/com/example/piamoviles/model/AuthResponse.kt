package com.example.piamoviles.model

data class AuthResponse(
    val mensaje: String,
    val token: String,
    val usuario: Usuario
)