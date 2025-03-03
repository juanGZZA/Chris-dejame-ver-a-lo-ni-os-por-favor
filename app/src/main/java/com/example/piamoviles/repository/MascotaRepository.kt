package com.example.piamoviles.repository

import com.example.piamoviles.model.Mascota
import com.example.piamoviles.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MascotaRepository {
    private val api = RetrofitClient.apiService


    fun getMascotas(onResult: (List<Mascota>?) -> Unit) {
        api.getMascotas().enqueue(object : Callback<List<Mascota>> {
            override fun onResponse(call: Call<List<Mascota>>, response: Response<List<Mascota>>) {
                println("Respuesta de la API: ${response.body()}")
                onResult(response.body())
            }
            override fun onFailure(call: Call<List<Mascota>>, t: Throwable) {
                println("Error en la API: ${t.message}")
                onResult(null)
            }
        })
    }

    fun agregarMascota(mascota: Mascota, onResult: () -> Unit) {
        api.addMascota(mascota).enqueue(object : Callback<Mascota> {
            override fun onResponse(call: Call<Mascota>, response: Response<Mascota>) {
                onResult()
            }
            override fun onFailure(call: Call<Mascota>, t: Throwable) {
                println("Error al agregar mascota: ${t.message}")
            }
        })
    }

    fun editarMascota(mascota: Mascota, onResult: (Boolean) -> Unit) {
        api.updateMascota(mascota.id, mascota).enqueue(object : Callback<Mascota> {
            override fun onResponse(call: Call<Mascota>, response: Response<Mascota>) {
                if (response.isSuccessful) {
                    onResult(true)
                } else {
                    onResult(false)
                }
            }

            override fun onFailure(call: Call<Mascota>, t: Throwable) {
                println("Error al actualizar mascota: ${t.message}")
                onResult(false)
            }
        })
    }


}
