package com.example.piamoviles.network

import com.example.piamoviles.model.AuthResponse
import com.example.piamoviles.model.LoginRequest
import com.example.piamoviles.model.RegisterRequest
import com.example.piamoviles.model.Usuario
import retrofit2.Call
import retrofit2.http.*

interface AuthService {
    @POST("login")
    fun login(@Body loginRequest: LoginRequest): Call<AuthResponse>

    @POST("registro")
    fun register(@Body registerRequest: RegisterRequest): Call<AuthResponse>

    @GET("usuario")
    fun getUserProfile(@Header("Authorization") token: String): Call<Usuario>
}