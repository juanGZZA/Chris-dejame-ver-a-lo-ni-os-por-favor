package com.example.piamoviles.pantallas

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.piamoviles.repository.AuthRepository

@Composable
fun InicioSesion(navController: NavController) {
    val context = LocalContext.current
    val authRepository = remember { AuthRepository(context) }
    var correoElectronico by remember { mutableStateOf("") }
    var contraseña by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    // Comprobar si ya está logueado
    LaunchedEffect(Unit) {
        if (authRepository.isLoggedIn()) {
            navController.navigate("mascotas") {
                popUpTo("inicio_sesion") { inclusive = true }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Iniciar Sesión", fontSize = 32.sp, color = Color(0xFF1976D2))
        Spacer(Modifier.padding(4.dp))
        Text("Registrate", fontSize = 16.sp, color = Color(0xFF1976D2), modifier = Modifier.clickable{
            navController.navigate("registro")
        })
        Spacer(Modifier.padding(12.dp))
        OutlinedTextField(
            value = correoElectronico,
            onValueChange = { correoElectronico = it },
            label = { Text("Correo Electrónico") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            enabled = !isLoading
        )
        Spacer(Modifier.padding(16.dp))
        OutlinedTextField(
            value = contraseña,
            onValueChange = { contraseña = it },
            label = { Text("Contraseña") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            enabled = !isLoading
        )

        if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Spacer(Modifier.padding(16.dp))
        ActionButton(
            text = if (isLoading) "Cargando..." else "Iniciar Sesión",
            onClick = {
                if (!isLoading && correoElectronico.isNotEmpty() && contraseña.isNotEmpty()) {
                    isLoading = true
                    errorMessage = null
                    authRepository.login(correoElectronico, contraseña) { success, message ->
                        isLoading = false
                        if (success) {
                            navController.navigate("mascotas") {
                                popUpTo("inicio_sesion") { inclusive = true }
                            }
                        } else {
                            errorMessage = message
                        }
                    }
                } else if (correoElectronico.isEmpty() || contraseña.isEmpty()) {
                    errorMessage = "Por favor complete todos los campos"
                }
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.padding(8.dp))
        Text("¿Olvidaste tu contraseña?",
            color = Color(0xFF1976D2),
            modifier = Modifier
                .clickable { navController.navigate("recuperar-contraseña") }
                .padding(8.dp)
        )
    }
}