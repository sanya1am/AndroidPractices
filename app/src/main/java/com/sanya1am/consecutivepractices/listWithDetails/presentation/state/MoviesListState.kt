package com.sanya1am.consecutivepractices.listWithDetails.presentation.state

import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieShortEntity

interface MoviesListState {
    val items: List<MovieShortEntity>
    val query: String
    val isEmpty: Boolean
}