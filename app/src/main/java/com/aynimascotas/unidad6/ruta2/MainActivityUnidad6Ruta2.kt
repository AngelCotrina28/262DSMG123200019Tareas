package com.aynimascotas.unidad6.ruta2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.aynimascotas.ui.theme.MyApplicationTheme
import com.aynimascotas.unidad6.ruta2.ui.InventoryApp

class MainActivityUnidad6Ruta2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
                InventoryApp()
            }
        }
    }
}
