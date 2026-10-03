package com.aynimascotas.unidad5.ruta2.bookshelf.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookResponse(
    val docs: List<BookDoc>? = null,
)

@Serializable
data class BookDoc(
    val key: String? = null,
    val title: String? = null,
    @SerialName("author_name") val authorName: List<String>? = null,
    @SerialName("cover_i") val coverI: Long? = null,
) {
    val coverUrl: String
        get() = if (coverI != null && coverI > 0) {
            "https://covers.openlibrary.org/b/id/$coverI-M.jpg"
        } else {
            "https://images.unsplash.com/photo-1544716278-ca5e3f4abd8c?w=300"
        }
}
