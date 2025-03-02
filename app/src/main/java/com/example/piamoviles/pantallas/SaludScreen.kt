package com.example.piamoviles.pantallas

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SaludScreen(navController: NavController) {
    PetDetailScreen(
        navController = navController,
        imageUrl = "https://i0.wp.com/www.ntv.com.mx/wp-content/uploads/2019/11/golden-cachorro-e1549967733842-1024x650.jpg?fit=1024%2C650&ssl=1",
        content = {
            Text("Peso: 15kg", Modifier.padding(8.dp))
            Text("Edad: 8 años", Modifier.padding(8.dp))
            Text("Raza: Chihuahua", Modifier.padding(8.dp))
            Text("Kilómetros recorridos: 2.1km", Modifier.padding(8.dp))
        },
        onEdit = { /* Lógica de edición */ }
    )
}