package com.aynimascotas.unidad4.ruta3.data

import com.aynimascotas.myapplication.R
import com.aynimascotas.unidad4.ruta3.model.Sport

object LocalSportsDataProvider {
    val defaultSport = Sport(
        id = 1,
        titleResourceId = R.string.football,
        subtitleResourceId = R.string.football_subtitle,
        imageResourceId = R.drawable.image1,
        sportsImageBanner = R.drawable.image1,
        newsDetails = R.string.football_desc,
    )

    fun getSportsData(): List<Sport> {
        return listOf(
            defaultSport,
            Sport(
                id = 2,
                titleResourceId = R.string.basketball,
                subtitleResourceId = R.string.basketball_subtitle,
                imageResourceId = R.drawable.image2,
                sportsImageBanner = R.drawable.image2,
                newsDetails = R.string.basketball_desc,
            ),
            Sport(
                id = 3,
                titleResourceId = R.string.tennis,
                subtitleResourceId = R.string.tennis_subtitle,
                imageResourceId = R.drawable.image3,
                sportsImageBanner = R.drawable.image3,
                newsDetails = R.string.tennis_desc,
            ),
            Sport(
                id = 4,
                titleResourceId = R.string.swimming,
                subtitleResourceId = R.string.swimming_subtitle,
                imageResourceId = R.drawable.image4,
                sportsImageBanner = R.drawable.image4,
                newsDetails = R.string.swimming_desc,
            ),
            Sport(
                id = 5,
                titleResourceId = R.string.athletics,
                subtitleResourceId = R.string.athletics_subtitle,
                imageResourceId = R.drawable.image5,
                sportsImageBanner = R.drawable.image5,
                newsDetails = R.string.athletics_desc,
            ),
            Sport(
                id = 6,
                titleResourceId = R.string.cycling,
                subtitleResourceId = R.string.cycling_subtitle,
                imageResourceId = R.drawable.image6,
                sportsImageBanner = R.drawable.image6,
                newsDetails = R.string.cycling_desc,
            ),
        )
    }
}
