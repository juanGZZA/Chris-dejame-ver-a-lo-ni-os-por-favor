// File: ApiService.kt
package com.example.piamoviles.network

import com.example.piamoviles.model.Mascota
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("mascotas")
    fun getMascotas(@Header("Authorization") token: String): Call<List<Mascota>>

    @GET("mascotas/{id}")
    fun getMascotaById(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Call<Mascota>

    @POST("mascotas")
    fun addMascota(
        @Header("Authorization") token: String,
        @Body mascota: Mascota
    ): Call<Mascota>

    @PUT("mascotas/{id}")
    fun updateMascota(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
        @Body mascota: Mascota
    ): Call<Mascota>

    @DELETE("mascotas/{id}")
    fun deleteMascota(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Call<Void>
}