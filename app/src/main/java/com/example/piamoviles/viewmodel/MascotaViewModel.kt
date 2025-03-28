package com.example.piamoviles.pantallas

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.piamoviles.model.Mascota
import com.example.piamoviles.repository.MascotaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MascotaViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MascotaRepository(application.applicationContext)
    private val _mascotas = MutableStateFlow<List<Mascota>>(emptyList())
    val mascotas: StateFlow<List<Mascota>> = _mascotas.asStateFlow()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    init {
        fetchMascotas()
    }

    fun fetchMascotas() {
        _loading.value = true
        _error.value = null

        repository.getMascotas { lista ->
            _loading.value = false
            if (lista != null) {
                println("Datos recibidos en ViewModel: $lista")
                _mascotas.value = lista
            } else {
                _error.value = "Error al cargar las mascotas"
            }
        }
    }

    fun agregarMascota(mascota: Mascota) {
        _loading.value = true
        _error.value = null

        repository.agregarMascota(mascota) { success ->
            _loading.value = false
            if (success) {
                fetchMascotas()
            } else {
                _error.value = "Error al agregar la mascota"
            }
        }
    }

    fun editarMascota(mascota: Mascota) {
        _loading.value = true
        _error.value = null

        repository.editarMascota(mascota) { success ->
            _loading.value = false
            if (success) {
                fetchMascotas()
            } else {
                _error.value = "Error al actualizar la mascota"
            }
        }
    }

    fun eliminarMascota(mascotaId: Int) {
        _loading.value = true
        _error.value = null

        repository.eliminarMascota(mascotaId) { success ->
            _loading.value = false
            if (success) {
                fetchMascotas()
            } else {
                _error.value = "Error al eliminar la mascota"
            }
        }
    }

    fun clearError() {
        _error.value = null
    }

    fun setError(error: String){
        _error.value = error
    }
}