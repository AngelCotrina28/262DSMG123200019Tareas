package com.aynimascotas.unidad3.ruta2.data

import com.aynimascotas.myapplication.R
import com.aynimascotas.unidad3.ruta2.model.Dog

object DogDatasource {
    val dogs = listOf(
        Dog(R.drawable.dog1, R.string.dog_name_1, 2, R.string.dog_description_1),
        Dog(R.drawable.dog2, R.string.dog_name_2, 16, R.string.dog_description_2),
        Dog(R.drawable.dog3, R.string.dog_name_3, 2, R.string.dog_description_3),
        Dog(R.drawable.dog4, R.string.dog_name_4, 8, R.string.dog_description_4),
        Dog(R.drawable.dog5, R.string.dog_name_5, 8, R.string.dog_description_5),
        Dog(R.drawable.dog6, R.string.dog_name_6, 14, R.string.dog_description_6),
    )
}
