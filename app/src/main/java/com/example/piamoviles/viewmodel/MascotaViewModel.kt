import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.piamoviles.model.Mascota
import com.example.piamoviles.repository.MascotaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MascotaViewModel : ViewModel() {
    private val repository = MascotaRepository()
    private val _mascotas = MutableStateFlow<List<Mascota>>(emptyList())
    val mascotas: StateFlow<List<Mascota>> = _mascotas.asStateFlow()

    init {
        fetchMascotas()
    }

    fun fetchMascotas() {
        repository.getMascotas { lista ->
            println("Datos recibidos en ViewModel: $lista")
            _mascotas.value = lista ?: emptyList()
        }
    }

    fun agregarMascota(mascota: Mascota) {
        repository.agregarMascota(mascota) { fetchMascotas() }
    }

    fun editarMascota(mascota: Mascota) {
        repository.editarMascota(mascota) { exito ->
            if (exito) fetchMascotas() // Recargar la lista si se actualizó correctamente
        }
    }

}
