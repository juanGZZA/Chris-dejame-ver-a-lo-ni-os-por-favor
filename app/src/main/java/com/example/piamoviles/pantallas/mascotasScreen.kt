package com.example.piamoviles.pantallas

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun mascotasScreen(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Mascotas", fontSize = 32.sp)
        Spacer(Modifier.padding(10.dp))
        LazyColumn {
            items(5) { pet ->
                PetCard(
                    petName = "Nombre",
                    imageUrl = "https://i0.wp.com/www.ntv.com.mx/wp-content/uploads/2019/11/golden-cachorro-e1549967733842-1024x650.jpg?fit=1024%2C650&ssl=1",
                    onClick = { navController.navigate("menu") }
                )
            }
        }
        ActionButton(
            text = "Registrar mascota",
            onClick = { /* Navegar a formulario */ },
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        )
    }
}