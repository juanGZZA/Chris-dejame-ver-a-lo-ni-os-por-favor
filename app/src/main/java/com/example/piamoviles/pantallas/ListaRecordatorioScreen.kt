package com.example.piamoviles.pantallas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items // Importación faltante
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add // Icono correcto
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.piamoviles.model.Recordatorio
import com.example.piamoviles.viewmodel.RecordatorioViewModel

@Composable
fun ListaRecordatoriosScreen(navController: NavController, mascotaId: Int) {
    val viewModel: RecordatorioViewModel = viewModel()
    val recordatorios by viewModel.recordatorios.collectAsState()
    var showDeleteDialog by remember { mutableStateOf(false) }
    var selectedRecordatorio by remember { mutableStateOf<Recordatorio?>(null) }

    LaunchedEffect(mascotaId) {
        viewModel.fetchRecordatorios()
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate("agregar-recordatorio/$mascotaId") }
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Agregar")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            items(recordatorios) { recordatorio ->
                RecordatorioItem(
                    recordatorio = recordatorio,
                    onDelete = {
                        selectedRecordatorio = recordatorio
                        showDeleteDialog = true
                    }
                )
            }
        }
    }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("Eliminar recordatorio") },
            text = { Text("¿Estás seguro de eliminar este recordatorio?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        selectedRecordatorio?.id?.let { viewModel.eliminarRecordatorio(it) }
                        showDeleteDialog = false
                    }
                ) {
                    Text("Eliminar", color = MaterialTheme.colorScheme.error)
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun RecordatorioItem(
    recordatorio: Recordatorio,
    onDelete: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = recordatorio.titulo,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = "Fecha: ${recordatorio.fecha} ${recordatorio.hora}",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = recordatorio.notas,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }

            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Eliminar recordatorio",
                    tint = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}