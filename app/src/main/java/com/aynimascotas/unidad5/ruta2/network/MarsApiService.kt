package com.aynimascotas.unidad5.ruta2.network

import com.aynimascotas.unidad5.ruta2.model.MarsPhoto
import retrofit2.http.GET

interface MarsApiService {
    @GET("photos")
    suspend fun getPhotos(): List<MarsPhoto>
}
