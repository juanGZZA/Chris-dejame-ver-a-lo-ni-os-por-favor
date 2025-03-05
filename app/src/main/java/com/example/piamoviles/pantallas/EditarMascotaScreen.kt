package com.example.piamoviles.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.piamoviles.model.Mascota

@Composable
fun EditarMascotaScreen(navController: NavController, mascotaId: Int) {
    val mascotaViewModel: MascotaViewModel = viewModel()
    val mascotas by mascotaViewModel.mascotas.collectAsState()
    val mascota = mascotas.firstOrNull { it.id == mascotaId } ?: return

    var nombre by remember { mutableStateOf(mascota.nombre) }
    var edad by remember { mutableStateOf(mascota.edad.toString()) }
    var informacion by remember { mutableStateOf(mascota.informacion) }
    var habitos by remember { mutableStateOf(mascota.habitos) }
    var salud by remember { mutableStateOf(mascota.salud) }
    var notas by remember { mutableStateOf(mascota.notas) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Editar Mascota", fontSize = 24.sp)
        Spacer(Modifier.padding(8.dp))

        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
        OutlinedTextField(value = edad, onValueChange = { edad = it }, label = { Text("Edad") })
        OutlinedTextField(value = informacion, onValueChange = { informacion = it }, label = { Text("Información") })
        OutlinedTextField(value = habitos, onValueChange = { habitos = it }, label = { Text("Hábitos") })
        OutlinedTextField(value = salud, onValueChange = { salud = it }, label = { Text("Salud") })
        OutlinedTextField(value = notas, onValueChange = { notas = it }, label = { Text("Notas") })

        Spacer(Modifier.padding(8.dp))

        ActionButton(
            text = "Guardar Cambios",
            onClick = {
                val mascotaActualizada = Mascota(
                    id = mascota.id,
                    nombre = nombre,
                    edad = edad.toIntOrNull() ?: mascota.edad,
                    informacion = informacion,
                    habitos = habitos,
                    salud = salud,
                    notas = notas
                )
                mascotaViewModel.editarMascota(mascotaActualizada)
                navController.popBackStack()
            }
        )

        Spacer(Modifier.padding(8.dp))

        ActionButton(
            text = "Cancelar",
            onClick = { navController.popBackStack() }
        )
    }
}
