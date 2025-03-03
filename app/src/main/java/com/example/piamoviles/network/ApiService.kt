package com.example.piamoviles.network

import com.example.piamoviles.model.Mascota
import retrofit2.Call
import retrofit2.http.*

interface ApiService {
    @GET("mascotas")
    fun getMascotas(): Call<List<Mascota>>

    @GET("mascotas/{id}")
    fun getMascotaById(@Path("id") id: Int): Call<Mascota>

    @POST("mascotas")
    fun addMascota(@Body mascota: Mascota): Call<Mascota>

    @PUT("mascotas/{id}")
    fun updateMascota(@Path("id") id: Int, @Body mascota: Mascota): Call<Mascota>

    @DELETE("mascotas/{id}")
    fun deleteMascota(@Path("id") id: Int): Call<Void>
}
