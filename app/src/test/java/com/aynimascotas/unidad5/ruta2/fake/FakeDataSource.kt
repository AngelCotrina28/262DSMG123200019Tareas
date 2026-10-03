package com.aynimascotas.unidad5.ruta2.fake

import com.aynimascotas.unidad5.ruta2.model.MarsPhoto

object FakeDataSource {
    val photosList = listOf(
        MarsPhoto(
            id = "1",
            imgSrc = "url.1",
        ),
        MarsPhoto(
            id = "2",
            imgSrc = "url.2",
        ),
    )
}
