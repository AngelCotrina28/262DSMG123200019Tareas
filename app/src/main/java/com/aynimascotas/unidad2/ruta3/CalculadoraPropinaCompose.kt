package com.aynimascotas.unidad2.ruta3

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.NumberFormat
import kotlin.math.ceil

@Composable
fun CalculadoraPropinaScreen() {
    var montoEntrada by remember { mutableStateOf("") }
    var porcentajePropinaEntrada by remember { mutableStateOf("15") }
    var redondearPropina by remember { mutableStateOf(false) }

    val monto = montoEntrada.toDoubleOrNull() ?: 0.0
    val porcentajePropina = porcentajePropinaEntrada.toDoubleOrNull() ?: 0.0
    val propina = calcularPropina(monto, porcentajePropina, redondearPropina)

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(horizontal = 40.dp)
            .verticalScroll(rememberScrollState())
            .safeDrawingPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Calcular Propina",
            modifier = Modifier
                .padding(bottom = 16.dp, top = 40.dp)
                .align(Alignment.Start),
            fontSize = 24.sp,
        )

        CampoTextoNumero(
            etiqueta = "Monto de la cuenta",
            valor = montoEntrada,
            alCambiarValor = { montoEntrada = it },
            opcionesTeclado = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Next,
            ),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
        )

        CampoTextoNumero(
            etiqueta = "Porcentaje de propina (%)",
            valor = porcentajePropinaEntrada,
            alCambiarValor = { porcentajePropinaEntrada = it },
            opcionesTeclado = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done,
            ),
            modifier = Modifier
                .padding(bottom = 32.dp)
                .fillMaxWidth(),
        )

        FilaRedondearPropina(
            redondear = redondearPropina,
            alCambiarRedondear = { redondearPropina = it },
            modifier = Modifier.padding(bottom = 32.dp),
        )

        Text(
            text = "Monto de la propina: $propina",
            style = MaterialTheme.typography.displaySmall,
            fontSize = 20.sp,
        )

        Spacer(modifier = Modifier.height(150.dp))
    }
}

@Composable
fun CampoTextoNumero(
    etiqueta: String,
    valor: String,
    alCambiarValor: (String) -> Unit,
    opcionesTeclado: KeyboardOptions,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = valor,
        onValueChange = alCambiarValor,
        singleLine = true,
        label = { Text(etiqueta) },
        keyboardOptions = opcionesTeclado,
        modifier = modifier,
    )
}

@Composable
fun FilaRedondearPropina(
    redondear: Boolean,
    alCambiarRedondear: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = "¿Redondear propina?",
            fontSize = 16.sp,
        )

        Switch(
            checked = redondear,
            onCheckedChange = alCambiarRedondear,
        )
    }
}

fun calcularPropina(monto: Double, porcentajePropina: Double = 15.0, redondear: Boolean = false): String {
    var propina = porcentajePropina / 100 * monto
    if (redondear) {
        propina = ceil(propina)
    }
    return NumberFormat.getCurrencyInstance().format(propina)
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CalculadoraPropinaPreview() {
    CalculadoraPropinaScreen()
}
