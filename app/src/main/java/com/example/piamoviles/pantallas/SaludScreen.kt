package com.example.piamoviles.pantallas

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun SaludScreen(navController: NavController, mascotaId: Int) {
    val mascotaViewModel: MascotaViewModel = viewModel()
    val mascotas by mascotaViewModel.mascotas.collectAsState()
    val mascota = mascotas.firstOrNull{ it.id == mascotaId}
    PetDetailScreen(
        navController = navController,
        imageUrl = "https://i0.wp.com/www.ntv.com.mx/wp-content/uploads/2019/11/golden-cachorro-e1549967733842-1024x650.jpg?fit=1024%2C650&ssl=1",
        content = {
            Text("Salud de ${mascota?.nombre}:", Modifier.padding(8.dp))
            Text(mascota?.salud ?: "", Modifier.padding(8.dp))
        }
    )
}