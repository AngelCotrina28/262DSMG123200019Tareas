package com.aynimascotas.unidad5.ruta2.data

import com.aynimascotas.unidad5.ruta2.model.MarsPhoto
import com.aynimascotas.unidad5.ruta2.network.MarsApiService

interface MarsPhotosRepository {
    suspend fun getMarsPhotos(): List<MarsPhoto>
}

class NetworkMarsPhotosRepository(
    private val marsApiService: MarsApiService,
) : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> = marsApiService.getPhotos()
}
