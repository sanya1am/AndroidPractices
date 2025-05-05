package com.sanya1am.consecutivepractices.favorites.presentation.state

import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieShortEntity

data class FavoritesState(
    val items: List<MovieShortEntity> = emptyList()
)
