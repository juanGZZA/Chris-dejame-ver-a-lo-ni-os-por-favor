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
import androidx.navigation.NavType
import androidx.navigation.navArgument

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
                        composable("recuperar-contraseña") {
                            ContraseñaOlvidada(navController)
                        }
                        composable("mascotas") {
                            mascotasScreen(navController)
                        }
                        composable(
                            "menu/{mascotaId}",
                            arguments = listOf(navArgument("mascotaId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val mascotaId = backStackEntry.arguments?.getInt("mascotaId")
                            if (mascotaId != null) {
                                MenuScreen(navController, mascotaId)
                            }
                        }
                        composable(
                            "salud/{mascotaId}",
                            arguments = listOf(navArgument("mascotaId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val mascotaId = backStackEntry.arguments?.getInt("mascotaId")
                            if (mascotaId != null) {
                                SaludScreen(navController, mascotaId)
                            }
                        }
                        composable(
                            "notas/{mascotaId}",
                            arguments = listOf(navArgument("mascotaId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val mascotaId = backStackEntry.arguments?.getInt("mascotaId")
                            if (mascotaId != null) {
                                NotasScreen(navController, mascotaId) // Pasa mascotaId
                            }
                        }
                        composable(
                            "informacion/{mascotaId}",
                            arguments = listOf(navArgument("mascotaId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val mascotaId = backStackEntry.arguments?.getInt("mascotaId")
                            if (mascotaId != null) {
                                InformacionScreen(navController, mascotaId) // Pasa mascotaId
                            }
                        }
                        composable(
                            "habitos/{mascotaId}",
                            arguments = listOf(navArgument("mascotaId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val mascotaId = backStackEntry.arguments?.getInt("mascotaId")
                            if (mascotaId != null) {
                                HabitosScreen(navController, mascotaId) // Pasa mascotaId
                            }
                        }
                        composable("registrar-mascota") {
                            RegistrarMascotaScreen(navController)
                        }
                        composable(
                            "editar-mascota/{mascotaId}",
                            arguments = listOf(navArgument("mascotaId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            val mascotaId = backStackEntry.arguments?.getInt("mascotaId")
                            if (mascotaId != null) {
                                EditarMascotaScreen(navController, mascotaId)
                            }
                        }

                    }
                }
            }
        }
    }
}



