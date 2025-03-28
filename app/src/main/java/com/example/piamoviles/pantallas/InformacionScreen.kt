package com.example.piamoviles.pantallas


import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun InformacionScreen(navController: NavController, mascotaId: Int) {
    val mascotaViewModel: MascotaViewModel = viewModel()
    val mascotas by mascotaViewModel.mascotas.collectAsState()
    val mascota = mascotas.firstOrNull{ it.id == mascotaId}
    PetDetailScreen(
        navController = navController,
        imageUrl = "https://i0.wp.com/www.ntv.com.mx/wp-content/uploads/2019/11/golden-cachorro-e1549967733842-1024x650.jpg?fit=1024%2C650&ssl=1",
        content = {
            Text("Informacion sobre ${mascota?.nombre}:", Modifier.padding(8.dp))
            Text(mascota?.informacion ?: "", Modifier.padding(8.dp))
            Text("Edad: ${mascota?.edad}", Modifier.padding(8.dp))
        }
    )
}