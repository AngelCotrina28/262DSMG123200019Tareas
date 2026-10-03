package com.aynimascotas.unidad6.ruta2.data

import com.aynimascotas.unidad6.ruta2.model.Item
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

interface ItemsRepository {
    fun getAllItemsStream(): Flow<List<Item>>
    fun getItemStream(id: Int): Flow<Item?>
    suspend fun insertItem(item: Item)
    suspend fun deleteItem(item: Item)
    suspend fun updateItem(item: Item)
}

class OfflineItemsRepository(private val itemDao: ItemDao) : ItemsRepository {
    override fun getAllItemsStream(): Flow<List<Item>> = itemDao.getAllItems()
    override fun getItemStream(id: Int): Flow<Item?> = itemDao.getItem(id)
    override suspend fun insertItem(item: Item) = withContext(Dispatchers.IO) {
        itemDao.insert(item)
    }

    override suspend fun deleteItem(item: Item) = withContext(Dispatchers.IO) {
        itemDao.delete(item)
    }

    override suspend fun updateItem(item: Item) = withContext(Dispatchers.IO) {
        itemDao.update(item)
    }
}
