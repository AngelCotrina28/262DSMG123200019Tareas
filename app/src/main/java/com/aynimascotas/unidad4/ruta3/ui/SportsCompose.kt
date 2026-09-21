package com.aynimascotas.unidad4.ruta3.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aynimascotas.myapplication.R
import com.aynimascotas.unidad4.ruta3.model.Sport

@Composable
fun SportsApp(
    viewModel: SportsViewModel = viewModel(),
) {
    val uiState by viewModel.uiState.collectAsState()
    val configuration = LocalConfiguration.current
    val isExpandedWidth = configuration.screenWidthDp >= 600

    Scaffold(
        topBar = {
            SportsTopAppBar(
                isShowingListPage = uiState.isShowingListPage,
                isExpandedWidth = isExpandedWidth,
                onBackFabClicked = { viewModel.navigateToListPage() },
            )
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            if (isExpandedWidth) {
                SportsListAndDetail(
                    sports = uiState.sportsList,
                    selectedSport = uiState.currentSport,
                    onClick = { viewModel.updateCurrentSport(it) },
                    modifier = Modifier.padding(horizontal = 16.dp),
                )
            } else {
                if (uiState.isShowingListPage) {
                    SportsList(
                        sports = uiState.sportsList,
                        onClick = { viewModel.updateCurrentSport(it) },
                        modifier = Modifier.padding(horizontal = 16.dp),
                    )
                } else {
                    SportsDetail(
                        selectedSport = uiState.currentSport,
                        onBackPressed = { viewModel.navigateToListPage() },
                        modifier = Modifier.padding(16.dp),
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SportsTopAppBar(
    isShowingListPage: Boolean,
    isExpandedWidth: Boolean,
    onBackFabClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = if (isExpandedWidth || isShowingListPage) {
                    stringResource(R.string.sports_app_name)
                } else {
                    stringResource(R.string.sports_detail)
                },
                style = MaterialTheme.typography.titleLarge,
            )
        },
        navigationIcon = {
            if (!isShowingListPage && !isExpandedWidth) {
                IconButton(onClick = onBackFabClicked) {
                    Icon(
                        painter = painterResource(R.drawable.ic_arrow_back),
                        contentDescription = "Atrás",
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        modifier = modifier,
    )
}

@Composable
fun SportsListAndDetail(
    sports: List<Sport>,
    selectedSport: Sport,
    onClick: (Sport) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.fillMaxSize()) {
        SportsList(
            sports = sports,
            onClick = onClick,
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp),
        )
        SportsDetail(
            selectedSport = selectedSport,
            onBackPressed = {},
            modifier = Modifier.weight(1.5f),
        )
    }
}

@Composable
fun SportsList(
    sports: List<Sport>,
    onClick: (Sport) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(sports, key = { sport -> sport.id }) { sport ->
            SportsListItem(
                sport = sport,
                onItemClick = onClick,
            )
        }
    }
}

@Composable
fun SportsListItem(
    sport: Sport,
    onItemClick: (Sport) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClick(sport) },
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = painterResource(sport.imageResourceId),
                contentDescription = null,
                modifier = Modifier
                    .size(64.dp)
                    .clip(RoundedCornerShape(8.dp)),
                contentScale = ContentScale.Crop,
            )
            Spacer(modifier = Modifier.size(16.dp))
            Column(
                modifier = Modifier.weight(1f),
            ) {
                Text(
                    text = stringResource(sport.titleResourceId),
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    text = stringResource(sport.subtitleResourceId),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
}

@Composable
fun SportsDetail(
    selectedSport: Sport,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
) {
    BackHandler {
        onBackPressed()
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(12.dp))
                .background(MaterialTheme.colorScheme.secondaryContainer),
        ) {
            Image(
                painter = painterResource(selectedSport.sportsImageBanner),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = stringResource(selectedSport.titleResourceId),
            style = MaterialTheme.typography.headlineMedium,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = stringResource(selectedSport.newsDetails),
            style = MaterialTheme.typography.bodyLarge,
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SportsAppPreview() {
    SportsApp()
}
