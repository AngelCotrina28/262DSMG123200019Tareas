package com.aynimascotas.unidad6.ruta2.data

import android.content.Context

interface InventoryAppContainer {
    val itemsRepository: ItemsRepository
}

class InventoryDefaultAppContainer(private val context: Context) : InventoryAppContainer {
    override val itemsRepository: ItemsRepository by lazy {
        OfflineItemsRepository(InventoryDatabase.getDatabase(context).itemDao())
    }
}
