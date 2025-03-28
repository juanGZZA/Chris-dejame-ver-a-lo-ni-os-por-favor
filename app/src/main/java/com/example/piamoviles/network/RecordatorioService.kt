package com.example.piamoviles.network

import com.example.piamoviles.model.Recordatorio
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

interface RecordatorioService {
    @POST("recordatorios")
    fun crearRecordatorio(
        @Header("Authorization") token: String,
        @Body recordatorio: Recordatorio
    ): Call<Recordatorio>

    @GET("recordatorios")
    fun obtenerRecordatorios(
        @Header("Authorization") token: String
    ): Call<List<Recordatorio>>

    @DELETE("recordatorios/{id}")
    fun eliminarRecordatorio(
        @Header("Authorization") token: String,
        @Path("id") id: Int
    ): Call<Void>
}

