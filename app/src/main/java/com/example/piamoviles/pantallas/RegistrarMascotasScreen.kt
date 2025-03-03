package com.example.piamoviles.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
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
fun RegistrarMascotaScreen(navController: NavController) {
    val mascotaViewModel: MascotaViewModel = viewModel()
    var nombre by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var informacion by remember { mutableStateOf("") }
    var habitos by remember { mutableStateOf("") }
    var salud by remember { mutableStateOf("") }
    var notas by remember { mutableStateOf("") }
    var imagenUrl by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Registrar Nueva Mascota", fontSize = 24.sp)
        Spacer(Modifier.padding(8.dp))
        OutlinedTextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
        OutlinedTextField(value = edad, onValueChange = { edad = it }, label = { Text("Edad") })
        OutlinedTextField(value = informacion, onValueChange = { informacion = it }, label = { Text("Información") })
        OutlinedTextField(value = habitos, onValueChange = { habitos = it }, label = { Text("Hábitos") })
        OutlinedTextField(value = salud, onValueChange = { salud = it }, label = { Text("Salud") })
        OutlinedTextField(value = notas, onValueChange = { notas = it }, label = { Text("Notas") })
        OutlinedTextField(value = imagenUrl, onValueChange = { imagenUrl = it }, label = { Text("Imagen URL") })

        Spacer(Modifier.padding(8.dp))
        ActionButton(
            text = "Registrar",
            onClick = {
                val nuevaMascota = Mascota(
                    id = 0, nombre, edad.toInt(), informacion, habitos, salud, notas
                )
                mascotaViewModel.agregarMascota(nuevaMascota)
                navController.popBackStack()
            }
        )
    }
}
