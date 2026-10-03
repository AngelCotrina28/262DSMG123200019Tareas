package com.aynimascotas.unidad6.ruta2.ui

import com.aynimascotas.unidad6.ruta2.model.Item
import java.text.NumberFormat

data class HomeUiState(val itemList: List<Item> = emptyList())

data class ItemUiState(
    val itemDetails: ItemDetails = ItemDetails(),
    val isEntryValid: Boolean = false,
)

data class ItemDetailsUiState(
    val outOfStock: Boolean = true,
    val itemDetails: ItemDetails = ItemDetails(),
)

data class ItemDetails(
    val id: Int = 0,
    val name: String = "",
    val price: String = "",
    val quantity: String = "",
)

fun ItemDetails.toItem(): Item = Item(
    id = id,
    name = name,
    price = price.toDoubleOrNull() ?: 0.0,
    quantity = quantity.toIntOrNull() ?: 0,
)

fun Item.toItemDetails(): ItemDetails = ItemDetails(
    id = id,
    name = name,
    price = price.toString(),
    quantity = quantity.toString(),
)

fun Item.formatedPrice(): String {
    return NumberFormat.getCurrencyInstance().format(price)
}
