package com.aynimascotas.unidad4.ruta2.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.aynimascotas.myapplication.R
import com.aynimascotas.unidad4.ruta2.data.OrderUiState

@Composable
fun OrderSummaryScreen(
    orderUiState: OrderUiState,
    onCancelButtonClicked: () -> Unit,
    onSendButtonClicked: (String, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val resources = LocalContext.current.resources
    val numberOfCupcakes = resources.getQuantityString(
        R.plurals.cupcakes,
        orderUiState.quantity,
        orderUiState.quantity,
    )
    val orderSummary = "Pedido: $numberOfCupcakes, Sabor: ${orderUiState.flavor}, Fecha: ${orderUiState.date}, Total: ${orderUiState.price}"
    val newOrderTitle = "Nuevo Pedido de Cupcakes"

    val items = listOf(
        Pair(stringResource(R.string.quantity), numberOfCupcakes),
        Pair(stringResource(R.string.flavor), orderUiState.flavor),
        Pair(stringResource(R.string.pickup_date), orderUiState.date),
    )

    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items.forEach { item ->
                Text(item.first.uppercase())
                Text(text = item.second, fontWeight = FontWeight.Bold)
                HorizontalDivider(thickness = 1.dp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = stringResource(R.string.subtotal_price, orderUiState.price),
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.align(Alignment.End),
            )
        }

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onSendButtonClicked(newOrderTitle, orderSummary) },
            ) {
                Text(stringResource(R.string.send_order))
            }
            OutlinedButton(
                modifier = Modifier.fillMaxWidth(),
                onClick = onCancelButtonClicked,
            ) {
                Text(stringResource(R.string.cancel))
            }
        }
    }
}
