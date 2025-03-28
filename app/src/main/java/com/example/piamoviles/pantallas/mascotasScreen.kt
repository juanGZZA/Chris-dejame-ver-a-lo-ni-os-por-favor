package com.example.piamoviles.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.piamoviles.repository.AuthRepository

@Composable
fun mascotasScreen(navController: NavController) {
    val context = LocalContext.current
    val authRepository = remember { AuthRepository(context) }
    val mascotaViewModel: MascotaViewModel = viewModel()
    val mascotas by mascotaViewModel.mascotas.collectAsState(initial = emptyList())
    val loading by mascotaViewModel.loading.observeAsState(initial = false)
    val error by mascotaViewModel.error.observeAsState(initial = null)
    val viewModel: MascotaViewModel = viewModel()

    // Comprobar si el usuario está autenticado
    LaunchedEffect(Unit) {
        if (!authRepository.isLoggedIn()) {
            navController.navigate("inicio_sesion") {
                popUpTo("mascotas") { inclusive = true }
            }
        } else {
            mascotaViewModel.fetchMascotas()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Mascotas", fontSize = 32.sp)

            // Botón de cerrar sesión
            IconButton(onClick = {
                authRepository.logout()
                navController.navigate("inicio_sesion") {
                    popUpTo("mascotas") { inclusive = true }
                }
            }) {
                Icon(Icons.Default.ExitToApp, contentDescription = "Cerrar sesión")
            }
        }

        Spacer(Modifier.padding(10.dp))

        if (loading) {
            CircularProgressIndicator(color = Color(0xFF3A8331))
        } else if (error != null) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(16.dp)
            ) {
                Text(error!!, color = Color.Red)
                Spacer(Modifier.padding(8.dp))
                ActionButton(
                    text = "Reintentar",
                    onClick = { mascotaViewModel.fetchMascotas() }
                )
            }
        } else if (mascotas.isEmpty()) {
            Text("No tienes mascotas registradas.", modifier = Modifier.padding(32.dp))
        } else {
            LazyColumn {
                items(mascotas) { mascota ->
                    PetCard(
                        petName = mascota.nombre,
                        imageUrl = "https://i0.wp.com/www.ntv.com.mx/wp-content/uploads/2019/11/golden-cachorro-e1549967733842-1024x650.jpg?fit=1024%2C650&ssl=1",
                        onClick = { navController.navigate("menu/${mascota.id}") },
                        onDelete = { viewModel.eliminarMascota(mascota.id) },
                    )
                }
            }
        }

        Spacer(Modifier.weight(1f))

        ActionButton(
            text = "Registrar mascota",
            onClick = { navController.navigate("registrar-mascota") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )
    }
}