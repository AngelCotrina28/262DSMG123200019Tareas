package com.aynimascotas.unidad5.ruta2.fake

import com.aynimascotas.unidad5.ruta2.data.MarsPhotosRepository
import com.aynimascotas.unidad5.ruta2.model.MarsPhoto

class FakeNetworkMarsPhotosRepository : MarsPhotosRepository {
    override suspend fun getMarsPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}
