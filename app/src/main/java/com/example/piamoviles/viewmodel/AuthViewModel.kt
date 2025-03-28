package com.example.piamoviles.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.piamoviles.model.Usuario
import com.example.piamoviles.repository.AuthRepository

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = AuthRepository(application.applicationContext)

    private val _loginStatus = MutableLiveData<LoginStatus>()
    val loginStatus: LiveData<LoginStatus> = _loginStatus

    private val _registerStatus = MutableLiveData<RegisterStatus>()
    val registerStatus: LiveData<RegisterStatus> = _registerStatus

    private val _currentUser = MutableLiveData<Usuario?>()
    val currentUser: LiveData<Usuario?> = _currentUser

    init {
        if (repository.isLoggedIn()) {
            _currentUser.value = repository.getCurrentUser()
        }
    }

    fun login(email: String, password: String) {
        _loginStatus.value = LoginStatus.Loading
        repository.login(email, password) { success, errorMessage ->
            if (success) {
                _loginStatus.value = LoginStatus.Success
                _currentUser.value = repository.getCurrentUser()
            } else {
                _loginStatus.value = LoginStatus.Error(errorMessage ?: "Error desconocido")
            }
        }
    }

    fun register(nombre: String, email: String, password: String) {
        _registerStatus.value = RegisterStatus.Loading
        repository.register(nombre, email, password) { success, errorMessage ->
            if (success) {
                _registerStatus.value = RegisterStatus.Success
                _currentUser.value = repository.getCurrentUser()
            } else {
                _registerStatus.value = RegisterStatus.Error(errorMessage ?: "Error desconocido")
            }
        }
    }

    fun logout() {
        repository.logout()
        _currentUser.value = null
    }

    fun refreshUserProfile() {
        repository.getUserProfile { usuario ->
            _currentUser.value = usuario
        }
    }

    fun isLoggedIn(): Boolean {
        return repository.isLoggedIn()
    }

    fun getAuthToken(): String? {
        return repository.getToken()
    }

    sealed class LoginStatus {
        object Loading : LoginStatus()
        object Success : LoginStatus()
        data class Error(val message: String) : LoginStatus()
    }

    sealed class RegisterStatus {
        object Loading : RegisterStatus()
        object Success : RegisterStatus()
        data class Error(val message: String) : RegisterStatus()
    }
}