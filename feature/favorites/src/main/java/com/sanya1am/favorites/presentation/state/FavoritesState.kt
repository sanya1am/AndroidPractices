package com.sanya1am.favorites.presentation.state

import com.sanya1am.api.domain.entity.MovieShortEntity

data class FavoritesState(
    val items: List<MovieShortEntity> = emptyList()
)
