package com.aynimascotas.unidad5.ruta2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.aynimascotas.ui.theme.MyApplicationTheme
import com.aynimascotas.unidad5.ruta2.amphibians.ui.AmphibiansApp
import com.aynimascotas.unidad5.ruta2.bookshelf.ui.BookshelfApp
import com.aynimascotas.unidad5.ruta2.ui.MarsPhotosApp

class MainActivityUnidad5Ruta2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                PantallaUnidad5Ruta2()
            }
        }
    }
}

@Composable
fun PantallaUnidad5Ruta2() {
    var pestanaSeleccionada by remember { mutableIntStateOf(0) }
    val titulosPestanas = listOf("Fotos Marte", "Anfibios", "Librería")

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            TabRow(selectedTabIndex = pestanaSeleccionada) {
                titulosPestanas.forEachIndexed { indice, titulo ->
                    Tab(
                        selected = pestanaSeleccionada == indice,
                        onClick = { pestanaSeleccionada = indice },
                        text = { Text(text = titulo, style = MaterialTheme.typography.titleSmall) },
                    )
                }
            }
        },
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            when (pestanaSeleccionada) {
                0 -> MarsPhotosApp()
                1 -> AmphibiansApp()
                2 -> BookshelfApp()
            }
        }
    }
}
