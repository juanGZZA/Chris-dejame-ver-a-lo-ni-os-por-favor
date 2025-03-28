package com.example.piamoviles.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.piamoviles.model.Mascota

@Composable
fun EditarMascotaScreen(navController: NavController, mascotaId: Int) {
    val mascotaViewModel: MascotaViewModel = viewModel()
    val mascotas by mascotaViewModel.mascotas.collectAsState()
    val loading by mascotaViewModel.loading.observeAsState(initial = false)
    val error by mascotaViewModel.error.observeAsState(initial = null)

    // Buscar la mascota con el ID proporcionado
    val mascota = mascotas.firstOrNull { it.id == mascotaId }

    // Estados para los campos de edición
    var nombre by remember { mutableStateOf(mascota?.nombre ?: "") }
    var edad by remember { mutableStateOf(mascota?.edad?.toString() ?: "") }
    var informacion by remember { mutableStateOf(mascota?.informacion ?: "") }
    var habitos by remember { mutableStateOf(mascota?.habitos ?: "") }
    var salud by remember { mutableStateOf(mascota?.salud ?: "") }
    var notas by remember { mutableStateOf(mascota?.notas ?: "") }

    // Si no se encontró la mascota, mostrar un mensaje y volver
    if (mascota == null) {
        LaunchedEffect(Unit) {
            mascotaViewModel.fetchMascotas()
        }

        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Cargando información de la mascota...")
            Spacer(Modifier.padding(16.dp))
            if (error != null) {
                Text("Error: $error", color = Color.Red)
                Spacer(Modifier.padding(16.dp))
                ActionButton(text = "Volver", onClick = { navController.popBackStack() })
            }
        }
        return
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Editar Mascota", fontSize = 24.sp)
        Spacer(Modifier.padding(8.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !loading
        )

        Spacer(Modifier.padding(8.dp))

        OutlinedTextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            enabled = !loading
        )

        Spacer(Modifier.padding(8.dp))

        OutlinedTextField(
            value = informacion,
            onValueChange = { informacion = it },
            label = { Text("Información") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !loading
        )

        Spacer(Modifier.padding(8.dp))

        OutlinedTextField(
            value = habitos,
            onValueChange = { habitos = it },
            label = { Text("Hábitos") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !loading
        )

        Spacer(Modifier.padding(8.dp))

        OutlinedTextField(
            value = salud,
            onValueChange = { salud = it },
            label = { Text("Salud") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !loading
        )

        Spacer(Modifier.padding(8.dp))

        OutlinedTextField(
            value = notas,
            onValueChange = { notas = it },
            label = { Text("Notas") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !loading
        )

        if (error != null) {
            Text(
                text = error!!,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(Modifier.padding(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ActionButton(
                text = "Cancelar",
                onClick = { navController.popBackStack() },
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            )

            ActionButton(
                text = if (loading) "Guardando..." else "Guardar Cambios",
                onClick = {
                    if (!loading && nombre.isNotEmpty() && edad.isNotEmpty()) {
                        val edadInt = edad.toIntOrNull() ?: 0
                        val mascotaActualizada = Mascota(
                            id = mascota.id,
                            nombre = nombre,
                            edad = edadInt,
                            informacion = informacion,
                            habitos = habitos,
                            salud = salud,
                            notas = notas
                        )
                        mascotaViewModel.editarMascota(mascotaActualizada)
                    } else {
                        mascotaViewModel.clearError()
                        mascotaViewModel.setError("Completa al menos nombre y edad")
                    }
                },
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            )
        }

        Spacer(Modifier.padding(16.dp))

        ActionButton(
            text = "Eliminar Mascota",
            onClick = { navController.navigate("confirmar-eliminar/${mascota.id}") },
            modifier = Modifier.fillMaxWidth()
        )
    }
}