package com.example.piamoviles.repository

import android.content.Context
import com.example.piamoviles.model.Recordatorio
import com.example.piamoviles.network.RetrofitClient
import com.example.piamoviles.network.RecordatorioService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecordatorioRepository(private val context: Context) {
    private val api: RecordatorioService = RetrofitClient.recordatorioService
    private val authRepository = AuthRepository(context)

    fun crearRecordatorio(recordatorio: Recordatorio, callback: (Boolean) -> Unit) {
        val token = authRepository.getToken()
        if (token == null) {
            callback(false)
            return
        }

        api.crearRecordatorio("Bearer $token", recordatorio).enqueue(object : Callback<Recordatorio> {
            override fun onResponse(call: Call<Recordatorio>, response: Response<Recordatorio>) {
                callback(response.isSuccessful)
            }

            override fun onFailure(call: Call<Recordatorio>, t: Throwable) {
                callback(false)
            }
        })
    }

    fun obtenerRecordatorios(callback: (List<Recordatorio>?) -> Unit) {
        val token = authRepository.getToken()
        if (token == null) {
            callback(null)
            return
        }

        api.obtenerRecordatorios("Bearer $token").enqueue(object : Callback<List<Recordatorio>> {
            override fun onResponse(call: Call<List<Recordatorio>>, response: Response<List<Recordatorio>>) {
                callback(response.body())
            }

            override fun onFailure(call: Call<List<Recordatorio>>, t: Throwable) {
                callback(null)
            }
        })
    }

    fun eliminarRecordatorio(id: Int, callback: (Boolean) -> Unit) {
        val token = authRepository.getToken()
        if (token == null) {
            callback(false)
            return
        }

        api.eliminarRecordatorio("Bearer $token", id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                callback(response.isSuccessful)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                callback(false)
            }
        })
    }
}