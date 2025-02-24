package com.example.piamoviles

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import java.nio.file.WatchEvent

@Composable
fun MenuScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxWidth().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter("https://i0.wp.com/www.ntv.com.mx/wp-content/uploads/2019/11/golden-cachorro-e1549967733842-1024x650.jpg?fit=1024%2C650&ssl=1"),
            contentDescription = null
        )
        Text("Monitorea a tu mascota", fontSize = 24.sp, modifier = Modifier.padding(16.dp))

        Button(
            onClick = {navController.navigate("salud")},
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3A8331),
                contentColor = Color.White
            )
        ) {
            Text("Salud", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = {navController.navigate("notas")},
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3A8331),
                contentColor = Color.White
            )
        ) {
            Text("Notas", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = {navController.navigate("informacion")},
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3A8331),
                contentColor = Color.White
            )
        ) {
            Text("Información", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = {navController.navigate("habitos")},
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3A8331),
                contentColor = Color.White
            )
        ) {
            Text("Habitos", fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.padding(20.dp))
        Button(
            onClick = {navController.popBackStack()},
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3A8331),
                contentColor = Color.White
            )
        ) {
            Text("Regresar", fontSize = 20.sp)
        }
    }
}