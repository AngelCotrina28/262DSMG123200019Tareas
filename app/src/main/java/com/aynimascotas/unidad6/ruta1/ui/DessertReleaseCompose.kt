package com.aynimascotas.unidad6.ruta1.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aynimascotas.myapplication.R
import com.aynimascotas.unidad6.ruta1.data.LocalDessertDataProvider

@Composable
fun DessertReleaseApp(
    viewModel: DessertReleaseViewModel = viewModel(factory = DessertReleaseViewModel.Factory),
) {
    val uiState by viewModel.uiState.collectAsState()

    Scaffold(
        topBar = {
            DessertReleaseTopAppBar(
                isLinearLayout = uiState.isLinearLayout,
                onSelectLayout = { viewModel.selectLayout(!uiState.isLinearLayout) },
            )
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            if (uiState.isLinearLayout) {
                DessertList(desserts = LocalDessertDataProvider.desserts)
            } else {
                DessertGrid(desserts = LocalDessertDataProvider.desserts)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DessertReleaseTopAppBar(
    isLinearLayout: Boolean,
    onSelectLayout: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "Dessert Release (DataStore)",
                style = MaterialTheme.typography.titleLarge,
            )
        },
        actions = {
            IconButton(onClick = onSelectLayout) {
                Icon(
                    painter = painterResource(
                        id = if (isLinearLayout) R.drawable.ic_grid_layout else R.drawable.ic_linear_layout,
                    ),
                    contentDescription = "Cambiar diseño",
                )
            }
        },
        modifier = modifier,
    )
}

@Composable
fun DessertList(desserts: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(desserts) { dessert ->
            DessertCard(dessert = dessert)
        }
    }
}

@Composable
fun DessertGrid(desserts: List<String>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(8.dp),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(desserts) { dessert ->
            DessertCard(dessert = dessert)
        }
    }
}

@Composable
fun DessertCard(dessert: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Text(
            text = dessert,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DessertReleasePreview() {
    DessertReleaseApp()
}
