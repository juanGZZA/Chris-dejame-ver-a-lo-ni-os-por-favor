package com.example.piamoviles

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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

@Composable
fun HabitosScreen(navController: NavController){
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter("https://i0.wp.com/www.ntv.com.mx/wp-content/uploads/2019/11/golden-cachorro-e1549967733842-1024x650.jpg?fit=1024%2C650&ssl=1"),
            contentDescription = null
        )
        Text("Max despierta temprano, generalmente cuando escucha movimiento en casa. Le gusta salir a pasear en las mañanas, olfatea todo a su paso y a veces intenta perseguir aves o mariposas. Es muy sociable con otros perros y personas, aunque ladra un poco cuando alguien desconocido se acerca demasiado. Come dos veces al día y disfruta especialmente la comida húmeda mezclada con croquetas. Toma siestas después de jugar, generalmente en su cama favorita o en algún rincón soleado. Le encanta masticar juguetes, pero a veces roba calcetines para jugar con ellos. Por la tarde, espera ansioso a que su dueño regrese, moviendo la cola emocionado al escuchar la puerta. Antes de dormir, busca caricias y se acomoda en su lugar habitual, suspirando profundamente antes de quedarse dormido.", fontSize = 16.sp, modifier = Modifier.padding(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {},
                modifier = Modifier
                    .wrapContentWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF3A8331),
                    contentColor = Color.White
                )
            ) {
                Text("Editar", fontSize = 20.sp)
            }
            Spacer(modifier = Modifier.padding(24.dp))
            Button(
                onClick = {navController.popBackStack()},
                modifier = Modifier
                    .wrapContentWidth()
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
}