package com.example.piamoviles.repository

import android.content.Context
import com.example.piamoviles.model.Mascota
import com.example.piamoviles.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MascotaRepository(private val context: Context) {
    private val api = RetrofitClient.apiService
    private val authRepository = AuthRepository(context)

    fun getMascotas(onResult: (List<Mascota>?) -> Unit) {
        val token = authRepository.getToken()
        if (token == null) {
            onResult(null)
            return
        }

        api.getMascotas("Bearer $token").enqueue(object : Callback<List<Mascota>> {
            override fun onResponse(call: Call<List<Mascota>>, response: Response<List<Mascota>>) {
                println("Respuesta de la API: ${response.code()}")
                if (response.isSuccessful) {
                    println("Mascotas recibidas: ${response.body()}")
                    onResult(response.body())
                } else {
                    println("Error: ${response.errorBody()?.string()}")
                    onResult(null)
                }
            }
            override fun onFailure(call: Call<List<Mascota>>, t: Throwable) {
                println("Error en la API: ${t.message}")
                onResult(null)
            }
        })
    }

    // En MascotaRepository.kt
    fun agregarMascota(mascota: Mascota, onResult: (Boolean) -> Unit) {
        val token = authRepository.getToken()
        if (token == null) {
            println("Error: Token nulo")
            onResult(false)
            return
        }

        println("Enviando mascota al servidor: $mascota")
        api.addMascota("Bearer $token", mascota).enqueue(object : Callback<Mascota> {
            override fun onResponse(call: Call<Mascota>, response: Response<Mascota>) {
                println("Respuesta del servidor: ${response.code()}")
                if (response.isSuccessful) {
                    println("Mascota agregada con éxito: ${response.body()}")
                    onResult(true)
                } else {
                    println("Error al agregar mascota. Código: ${response.code()}, Mensaje: ${response.errorBody()?.string()}")
                    onResult(false)
                }
            }
            override fun onFailure(call: Call<Mascota>, t: Throwable) {
                println("Error al agregar mascota: ${t.message}")
                t.printStackTrace()
                onResult(false)
            }
        })
    }

    fun editarMascota(mascota: Mascota, onResult: (Boolean) -> Unit) {
        val token = authRepository.getToken()
        if (token == null) {
            onResult(false)
            return
        }

        api.updateMascota("Bearer $token", mascota.id, mascota).enqueue(object : Callback<Mascota> {
            override fun onResponse(call: Call<Mascota>, response: Response<Mascota>) {
                onResult(response.isSuccessful)
            }

            override fun onFailure(call: Call<Mascota>, t: Throwable) {
                println("Error al actualizar mascota: ${t.message}")
                onResult(false)
            }
        })
    }

    fun eliminarMascota(mascotaId: Int, onResult: (Boolean) -> Unit) {
        val token = authRepository.getToken()
        if (token == null) {
            onResult(false)
            return
        }

        api.deleteMascota("Bearer $token", mascotaId).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                onResult(response.isSuccessful)
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                println("Error al eliminar mascota: ${t.message}")
                onResult(false)
            }
        })
    }
}