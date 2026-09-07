package com.aynimascotas.unidad2.ruta2

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aynimascotas.myapplication.R

@Composable
fun LanzadorDadosScreen(modifier: Modifier = Modifier) {
    var resultado by remember { mutableIntStateOf(1) }

    val imagenResource = when (resultado) {
        1 -> R.drawable.dice_1
        2 -> R.drawable.dice_2
        3 -> R.drawable.dice_3
        4 -> R.drawable.dice_4
        5 -> R.drawable.dice_5
        else -> R.drawable.dice_6
    }

    Column(
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Image(
            painter = painterResource(id = imagenResource),
            contentDescription = "Dado con valor $resultado",
            modifier = Modifier.size(180.dp),
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { resultado = (1..6).random() },
        ) {
            Text(
                text = "Tirar",
                fontSize = 20.sp,
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LanzadorDadosPreview() {
    LanzadorDadosScreen()
}
