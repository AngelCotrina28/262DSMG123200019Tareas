package com.aynimascotas.unidad5.ruta1.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aynimascotas.myapplication.R

@Composable
fun MarsPhotosApp() {
    Scaffold(
        topBar = {
            MarsTopAppBar()
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            val marsViewModel: MarsViewModel = viewModel()
            HomeScreen(
                marsUiState = marsViewModel.marsUiState,
                retryAction = marsViewModel::getMarsPhotos,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarsTopAppBar(modifier: Modifier = Modifier) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Fotos de Marte",
                style = MaterialTheme.typography.headlineMedium,
            )
        },
        modifier = modifier,
    )
}

@Composable
fun HomeScreen(
    marsUiState: MarsUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    when (marsUiState) {
        is MarsUiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
        is MarsUiState.Success -> SuccessScreen(marsUiState.photos, modifier = modifier.fillMaxSize())
        is MarsUiState.Error -> ErrorScreen(retryAction = retryAction, modifier = modifier.fillMaxSize())
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(64.dp),
        )
    }
}

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = "Error de conexión",
            modifier = Modifier.size(120.dp),
        )
        Text(
            text = "Fallo al cargar datos de Internet",
            modifier = Modifier.padding(16.dp),
        )
        Button(onClick = retryAction) {
            Text("Reintentar")
        }
    }
}

@Composable
fun SuccessScreen(photos: String, modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.padding(16.dp),
    ) {
        Text(
            text = photos,
            style = MaterialTheme.typography.titleLarge,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ResultScreenPreview() {
    HomeScreen(
        marsUiState = MarsUiState.Success("¡Éxito! Se recibieron 837 fotos de Marte desde la API."),
        retryAction = {},
    )
}
