package com.example.piamoviles.pantallas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.piamoviles.ui.theme.PIAMovilesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PIAMovilesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0xFFE3F2FD)
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "inicio_sesion"
                    ) {
                        composable("inicio_sesion") {
                            InicioSesion(navController)
                        }
                        composable("registro") {
                            Registro(navController)
                        }
                        composable("recuperar-contraseña"){
                            ContraseñaOlvidada(navController)
                        }
                        composable("mascotas"){
                            mascotasScreen(navController)
                        }
                        composable("menu"){
                            MenuScreen(navController)
                        }
                        composable("salud"){
                            SaludScreen(navController)
                        }
                        composable("notas"){
                            NotasScreen(navController)
                        }
                        composable("informacion"){
                            InformacionScreen(navController)
                        }
                        composable("habitos"){
                            HabitosScreen(navController)
                        }
                    }
                }
            }
        }
    }
}



