package com.aynimascotas.myapplication3

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aynimascotas.myapplication.R

@Composable
fun TarjetaPresentacionScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFFD2E8D4))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "Icono de perfil de usuario",
                modifier = Modifier
                    .size(120.dp)
                    .background(Color(0xFF073042))
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Angel Cotrina",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B1B1B)
            )

            Text(
                text = "Desarrollador Movil",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF006D3B),
                textAlign = TextAlign.Center
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ContactoRow(texto = "(+51)994896318")
            ContactoRow(texto = "@angel.cotrina28")
            ContactoRow(texto = "angel.cotrina@unmsm.edu.pe")
        }
    }
}

@Composable
fun ContactoRow(texto: String) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = texto,
            fontSize = 14.sp,
            color = Color(0xFF2D312E)
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TarjetaPresentacionPreview() {
    TarjetaPresentacionScreen()
}
