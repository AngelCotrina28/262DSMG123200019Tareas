package com.aynimascotas.unidad6.ruta3.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aynimascotas.unidad6.ruta3.model.BusSchedule
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun BusScheduleApp(
    viewModel: BusScheduleViewModel = viewModel(factory = BusScheduleViewModel.Factory),
) {
    var filtroEstacion by remember { mutableStateOf("") }
    val listaHorarios by if (filtroEstacion.isBlank()) {
        viewModel.getFullSchedule()
    } else {
        viewModel.getScheduleFor(filtroEstacion)
    }.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            BusScheduleTopAppBar()
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                OutlinedTextField(
                    value = filtroEstacion,
                    onValueChange = { filtroEstacion = it },
                    label = { Text("Buscar por estación") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    singleLine = true,
                )

                if (listaHorarios.isEmpty()) {
                    Text(
                        text = "No hay horarios registrados.",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(16.dp),
                    )
                } else {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(items = listaHorarios, key = { it.id }) { schedule ->
                            BusScheduleCard(
                                schedule = schedule,
                                onScheduleClick = { filtroEstacion = schedule.stopName },
                            )
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BusScheduleTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Horarios de Autobuses",
                style = MaterialTheme.typography.titleLarge,
            )
        },
        modifier = modifier,
    )
}

@Composable
fun BusScheduleCard(
    schedule: BusSchedule,
    onScheduleClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val horaFormateada = remember(schedule.arrivalTimeTimestamp) {
        val date = Date(schedule.arrivalTimeTimestamp.toLong() * 1000)
        SimpleDateFormat("hh:mm a", Locale.getDefault()).format(date)
    }

    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onScheduleClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = schedule.stopName,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
            )
            Text(
                text = horaFormateada,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun BusSchedulePreview() {
    BusScheduleApp()
}
