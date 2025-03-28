package com.example.piamoviles.repository

import android.content.Context
import android.content.SharedPreferences
import com.example.piamoviles.model.AuthResponse
import com.example.piamoviles.model.LoginRequest
import com.example.piamoviles.model.RegisterRequest
import com.example.piamoviles.model.Usuario
import com.example.piamoviles.network.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository(context: Context) {
    private val api = RetrofitClient.authService
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    fun login(email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        val loginRequest = LoginRequest(email, password)
        api.login(loginRequest).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val authResponse = response.body()!!
                    // Guardar el token en SharedPreferences
                    saveToken(authResponse.token)
                    saveUser(authResponse.usuario)
                    onResult(true, null)
                } else {
                    onResult(false, "Credenciales inválidas")
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                onResult(false, "Error de conexión: ${t.message}")
            }
        })
    }

    fun register(nombre: String, email: String, password: String, onResult: (Boolean, String?) -> Unit) {
        val registerRequest = RegisterRequest(nombre, email, password)
        api.register(registerRequest).enqueue(object : Callback<AuthResponse> {
            override fun onResponse(call: Call<AuthResponse>, response: Response<AuthResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    val authResponse = response.body()!!
                    // Guardar el token en SharedPreferences
                    saveToken(authResponse.token)
                    saveUser(authResponse.usuario)
                    onResult(true, null)
                } else {
                    onResult(false, "Error al registrar usuario")
                }
            }

            override fun onFailure(call: Call<AuthResponse>, t: Throwable) {
                onResult(false, "Error de conexión: ${t.message}")
            }
        })
    }

    fun getUserProfile(onResult: (Usuario?) -> Unit) {
        val token = getToken()
        if (token == null) {
            onResult(null)
            return
        }

        api.getUserProfile("Bearer $token").enqueue(object : Callback<Usuario> {
            override fun onResponse(call: Call<Usuario>, response: Response<Usuario>) {
                if (response.isSuccessful) {
                    onResult(response.body())
                } else {
                    onResult(null)
                }
            }

            override fun onFailure(call: Call<Usuario>, t: Throwable) {
                onResult(null)
            }
        })
    }

    fun logout() {
        clearToken()
        clearUser()
    }

    fun isLoggedIn(): Boolean {
        return getToken() != null
    }

    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    private fun saveToken(token: String) {
        sharedPreferences.edit().putString("token", token).apply()
    }

    private fun clearToken() {
        sharedPreferences.edit().remove("token").apply()
    }

    private fun saveUser(usuario: Usuario) {
        sharedPreferences.edit()
            .putInt("userId", usuario.id ?: 0)
            .putString("userName", usuario.nombre)
            .putString("userEmail", usuario.email)
            .apply()
    }

    private fun clearUser() {
        sharedPreferences.edit()
            .remove("userId")
            .remove("userName")
            .remove("userEmail")
            .apply()
    }

    fun getCurrentUser(): Usuario? {
        val id = sharedPreferences.getInt("userId", -1)
        if (id == -1) return null

        val nombre = sharedPreferences.getString("userName", "") ?: ""
        val email = sharedPreferences.getString("userEmail", "") ?: ""

        return Usuario(id, nombre, email)
    }
}