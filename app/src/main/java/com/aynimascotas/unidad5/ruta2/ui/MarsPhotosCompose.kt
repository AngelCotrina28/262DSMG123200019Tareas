package com.aynimascotas.unidad5.ruta2.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.aynimascotas.myapplication.R
import com.aynimascotas.unidad5.ruta2.model.MarsPhoto

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
            val marsViewModel: MarsViewModel = viewModel(factory = MarsViewModel.Factory)
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
                text = "Fotos de Marte - Galería Coil",
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
        is MarsUiState.Success -> PhotosGridScreen(marsUiState.photos, modifier = modifier.fillMaxSize())
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
            contentDescription = "Error",
            modifier = Modifier.size(120.dp),
        )
        Text(
            text = "Fallo de red al descargar imágenes",
            modifier = Modifier.padding(16.dp),
        )
        Button(onClick = retryAction) {
            Text("Reintentar")
        }
    }
}

@Composable
fun PhotosGridScreen(
    photos: List<MarsPhoto>,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(150.dp),
        modifier = modifier.padding(horizontal = 4.dp),
        contentPadding = PaddingValues(4.dp),
    ) {
        items(items = photos, key = { photo -> photo.id }) { photo ->
            MarsPhotoCard(
                photo = photo,
                modifier = Modifier
                    .padding(4.dp)
                    .fillMaxWidth()
                    .aspectRatio(1.5f),
            )
        }
    }
}

@Composable
fun MarsPhotoCard(
    photo: MarsPhoto,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(context = LocalContext.current)
                .data(photo.imgSrc)
                .crossfade(true)
                .build(),
            contentDescription = "Foto de Marte",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth(),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            error = painterResource(R.drawable.ic_launcher_foreground),
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ResultScreenPreview() {
    HomeScreen(
        marsUiState = MarsUiState.Success(
            listOf(
                MarsPhoto("1", "https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000MR0004450000100057E01_DXXX.jpg"),
                MarsPhoto("2", "https://mars.jpl.nasa.gov/msl-raw-images/msss/01000/mcam/1000ML0004450000100057E01_DXXX.jpg"),
            ),
        ),
        retryAction = {},
    )
}
