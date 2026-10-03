package com.aynimascotas.unidad5.ruta2.fake

import com.aynimascotas.unidad5.ruta2.model.MarsPhoto
import com.aynimascotas.unidad5.ruta2.network.MarsApiService

class FakeMarsApiService : MarsApiService {
    override suspend fun getPhotos(): List<MarsPhoto> {
        return FakeDataSource.photosList
    }
}
