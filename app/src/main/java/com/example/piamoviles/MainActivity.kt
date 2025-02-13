package com.example.piamoviles

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.piamoviles.ui.theme.PIAMovilesTheme
import java.nio.file.WatchEvent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PIAMovilesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background) {
                    InicioSesion()

                }
            }
        }
    }
}

@Composable
fun InicioSesion(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.padding(vertical = 10.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Iniciar Sesion", fontSize = 32.sp)
            Row {
                Text("¿Es tu primera vez?")
                Text("Registrate",
                    modifier = Modifier.padding(4.dp).clickable {
                        // Aquí puedes agregar una acción al hacer clic en "Registrate"
                    },
                    style = TextStyle(textDecoration = TextDecoration.Underline),
                    )
            }
        }
        Column(
            modifier = Modifier.padding(vertical = 10.dp)
        ) {
            Text("Email:")
            OutlinedTextField(value = "Email", onValueChange = {})
        }
        Column(
            modifier = Modifier.padding(10.dp)
        ) {
            Text("Contraseña:")
            OutlinedTextField(value = "Contraseña", onValueChange = {})
            Text("¿Olvidaste tu contraseña?",
                modifier = Modifier.padding(vertical = 10.dp).clickable {},
                style = TextStyle(textDecoration = TextDecoration.Underline))
        }
        val context = LocalContext.current
        Button(onClick = {
            Toast.makeText(
                context,
                "Iniciaste sesion",
                Toast.LENGTH_LONG
            ).show()
        }) {
            Text("Inicar Sesion")
        }

    }
}