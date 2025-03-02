package com.example.piamoviles.pantallas

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun MenuScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter("https://i0.wp.com/www.ntv.com.mx/wp-content/uploads/2019/11/golden-cachorro-e1549967733842-1024x650.jpg?fit=1024%2C650&ssl=1"),
            contentDescription = null
        )
        Text("Monitorea a tu mascota", fontSize = 24.sp, modifier = Modifier.padding(16.dp))

        ActionButton(
            text = "Salud",
            onClick = { navController.navigate("salud") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.padding(10.dp))
        ActionButton(
            text = "Notas",
            onClick = { navController.navigate("notas") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.padding(10.dp))
        ActionButton(
            text = "Información",
            onClick = { navController.navigate("informacion") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.padding(10.dp))
        ActionButton(
            text = "Hábitos",
            onClick = { navController.navigate("habitos") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.padding(20.dp))
        ActionButton(
            text = "Regresar",
            onClick = { navController.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        )
    }
}