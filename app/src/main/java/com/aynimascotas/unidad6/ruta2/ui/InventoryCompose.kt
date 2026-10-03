package com.aynimascotas.unidad6.ruta2.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.aynimascotas.myapplication.R
import com.aynimascotas.unidad6.ruta2.model.Item
import kotlinx.coroutines.launch

enum class InventoryScreen {
    Home,
    Entry,
    Details,
}

@Composable
fun InventoryApp(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = InventoryScreen.Home.name,
        modifier = Modifier.fillMaxSize(),
    ) {
        composable(route = InventoryScreen.Home.name) {
            HomeScreen(
                navigateToItemEntry = { navController.navigate(InventoryScreen.Entry.name) },
                navigateToItemUpdate = { itemId -> navController.navigate("${InventoryScreen.Details.name}/$itemId") },
            )
        }
        composable(route = InventoryScreen.Entry.name) {
            ItemEntryScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() },
            )
        }
        composable(
            route = "${InventoryScreen.Details.name}/{itemId}",
            arguments = listOf(navArgument("itemId") { type = NavType.IntType }),
        ) {
            ItemDetailsScreen(
                navigateBack = { navController.popBackStack() },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navigateToItemEntry: () -> Unit,
    navigateToItemUpdate: (Int) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory),
) {
    val homeUiState by viewModel.homeUiState.collectAsState()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Inventario Room", style = MaterialTheme.typography.titleLarge) },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToItemEntry,
                modifier = Modifier.padding(20.dp),
            ) {
                Icon(painter = painterResource(R.drawable.ic_grid_layout), contentDescription = "Agregar producto")
            }
        },
    ) { innerPadding ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            if (homeUiState.itemList.isEmpty()) {
                BoxNoItems()
            } else {
                InventoryList(
                    itemList = homeUiState.itemList,
                    onItemClick = { navigateToItemUpdate(it.id) },
                )
            }
        }
    }
}

@Composable
fun BoxNoItems(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "No hay productos en la base de datos Room.",
            style = MaterialTheme.typography.titleMedium,
        )
    }
}

@Composable
fun InventoryList(
    itemList: List<Item>,
    onItemClick: (Item) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items = itemList, key = { it.id }) { item ->
            InventoryItemCard(item = item, modifier = Modifier.clickable { onItemClick(item) })
        }
    }
}

@Composable
fun InventoryItemCard(item: Item, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = item.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                Text(text = "Stock: ${item.quantity}")
            }
            Text(text = item.formatedPrice(), fontWeight = FontWeight.Bold, fontSize = 16.sp)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemEntryScreen(
    navigateBack: () -> Unit,
    onNavigateUp: () -> Unit,
    viewModel: ItemEntryViewModel = viewModel(factory = ItemEntryViewModel.Factory),
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Agregar Producto") },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(painter = painterResource(R.drawable.ic_arrow_back), contentDescription = "Atrás")
                    }
                },
            )
        },
    ) { innerPadding ->
        ItemEntryBody(
            itemUiState = viewModel.itemUiState,
            onItemValueChange = viewModel::updateUiState,
            onSaveClick = {
                coroutineScope.launch {
                    viewModel.saveItem()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        )
    }
}

@Composable
fun ItemEntryBody(
    itemUiState: ItemUiState,
    onItemValueChange: (ItemDetails) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        ItemInputForm(
            itemDetails = itemUiState.itemDetails,
            onValueChange = onItemValueChange,
            modifier = Modifier.fillMaxWidth(),
        )
        Button(
            onClick = onSaveClick,
            enabled = itemUiState.isEntryValid,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Guardar en Room")
        }
    }
}

@Composable
fun ItemInputForm(
    itemDetails: ItemDetails,
    onValueChange: (ItemDetails) -> Unit = {},
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        OutlinedTextField(
            value = itemDetails.name,
            onValueChange = { onValueChange(itemDetails.copy(name = it)) },
            label = { Text("Nombre del producto") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
        OutlinedTextField(
            value = itemDetails.price,
            onValueChange = { onValueChange(itemDetails.copy(price = it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            label = { Text("Precio (S/)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
        OutlinedTextField(
            value = itemDetails.quantity,
            onValueChange = { onValueChange(itemDetails.copy(quantity = it)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            label = { Text("Cantidad en stock") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemDetailsScreen(
    navigateBack: () -> Unit,
    viewModel: ItemDetailsViewModel = viewModel(factory = ItemDetailsViewModel.Factory),
) {
    val uiState by viewModel.uiState.collectAsState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Detalle de Producto") },
                navigationIcon = {
                    IconButton(onClick = navigateBack) {
                        Icon(painter = painterResource(R.drawable.ic_arrow_back), contentDescription = "Atrás")
                    }
                },
            )
        },
    ) { innerPadding ->
        ItemDetailsBody(
            itemDetailsUiState = uiState,
            onSellItem = { viewModel.reduceQuantityByOne() },
            onDelete = {
                coroutineScope.launch {
                    viewModel.deleteItem()
                    navigateBack()
                }
            },
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
        )
    }
}

@Composable
fun ItemDetailsBody(
    itemDetailsUiState: ItemDetailsUiState,
    onSellItem: () -> Unit,
    onDelete: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        ItemDetailsCard(
            item = itemDetailsUiState.itemDetails.toItem(),
            modifier = Modifier.fillMaxWidth(),
        )
        Button(
            onClick = onSellItem,
            modifier = Modifier.fillMaxWidth(),
            enabled = !itemDetailsUiState.outOfStock,
        ) {
            Text("Vender 1 unidad")
        }
        OutlinedButton(
            onClick = onDelete,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Eliminar de la base de datos")
        }
    }
}

@Composable
fun ItemDetailsCard(
    item: Item,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Text(text = "NOMBRE: ${item.name}", fontWeight = FontWeight.Bold)
            Text(text = "PRECIO: ${item.formatedPrice()}")
            Text(text = "CANTIDAD EN STOCK: ${item.quantity}")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun InventoryAppPreview() {
    InventoryApp()
}
