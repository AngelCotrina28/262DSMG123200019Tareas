package com.aynimascotas.unidad2.ruta1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aynimascotas.ui.theme.MyApplicationTheme

class MainActivityUnidad2Ruta1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val resultadoEjercicios = ejecutarTodosLosEjercicios()

        setContent {
            MyApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    PantallaFundamentosKotlin(
                        resultado = resultadoEjercicios,
                        modifier = Modifier
                            .padding(innerPadding)
                            .padding(16.dp),
                    )
                }
            }
        }
    }
}

@Composable
fun PantallaFundamentosKotlin(resultado: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Text(
            text = resultado,
            fontSize = 14.sp,
            fontFamily = FontFamily.Monospace,
        )
    }
}
