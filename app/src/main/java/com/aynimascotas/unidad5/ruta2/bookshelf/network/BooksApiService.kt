package com.aynimascotas.unidad5.ruta2.bookshelf.network

import com.aynimascotas.unidad5.ruta2.bookshelf.model.BookResponse
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://openlibrary.org/"

private val json by lazy {
    Json {
        ignoreUnknownKeys = true
        coerceInputValues = true
        isLenient = true
    }
}

private val retrofit by lazy {
    Retrofit.Builder()
        .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(BASE_URL)
        .build()
}

interface BooksApiService {
    @GET("search.json")
    suspend fun searchBooks(
        @Query("q") query: String = "kotlin",
        @Query("limit") limit: Int = 20,
    ): BookResponse
}

object BooksApi {
    val retrofitService: BooksApiService by lazy {
        retrofit.create(BooksApiService::class.java)
    }
}
