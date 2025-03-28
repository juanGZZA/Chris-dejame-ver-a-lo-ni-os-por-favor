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
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.piamoviles.model.Mascota
import com.example.piamoviles.repository.AuthRepository

@Composable
fun RegistrarMascotaScreen(navController: NavController) {
    val context = LocalContext.current
    val authRepository = remember { AuthRepository(context) }
    val mascotaViewModel: MascotaViewModel = viewModel()
    val loading by mascotaViewModel.loading.observeAsState(initial = false)
    val error by mascotaViewModel.error.observeAsState(initial = null)

    var nombre by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var informacion by remember { mutableStateOf("") }
    var habitos by remember { mutableStateOf("") }
    var salud by remember { mutableStateOf("") }
    var notas by remember { mutableStateOf("") }
    var img by remember { mutableStateOf("") } // Añadido para el campo de imagen

    // Comprobar autenticación
    LaunchedEffect(Unit) {
        if (!authRepository.isLoggedIn()) {
            navController.navigate("inicio_sesion")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Registrar Nueva Mascota", fontSize = 24.sp)
        Spacer(Modifier.padding(8.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !loading
        )

        OutlinedTextField(
            value = edad,
            onValueChange = { edad = it },
            label = { Text("Edad") },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            enabled = !loading
        )

        OutlinedTextField(
            value = informacion,
            onValueChange = { informacion = it },
            label = { Text("Información") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !loading
        )

        OutlinedTextField(
            value = habitos,
            onValueChange = { habitos = it },
            label = { Text("Hábitos") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !loading
        )

        OutlinedTextField(
            value = salud,
            onValueChange = { salud = it },
            label = { Text("Salud") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !loading
        )

        OutlinedTextField(
            value = notas,
            onValueChange = { notas = it },
            label = { Text("Notas") },
            modifier = Modifier.fillMaxWidth(),
            enabled = !loading
        )

        // Opcional: campo para URL de imagen
        OutlinedTextField(
            value = img,
            onValueChange = { img = it },
            label = { Text("URL de imagen (opcional)") },
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
                text = if (loading) "Guardando..." else "Registrar",
                onClick = {
                    if (!loading && nombre.isNotEmpty() && edad.isNotEmpty()) {
                        val edadInt = edad.toIntOrNull() ?: 0
                        val nuevaMascota = Mascota(
                            id = 0,
                            nombre = nombre,
                            edad = edadInt,
                            informacion = informacion,
                            habitos = habitos,
                            salud = salud,
                            notas = notas,
                            img = img // Incluir el campo img
                        )
                        mascotaViewModel.agregarMascota(nuevaMascota)
                        // La navegación se maneja en el ViewModel cuando se completa la operación
                    } else {
                        mascotaViewModel.setError("Por favor completa al menos los campos de nombre y edad")
                    }
                    navController.popBackStack()
                },
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            )
        }
    }
}