package com.sanya1am.consecutivepractices.listWithDetails.presentation.state

import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieShortEntity
import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieType

interface MoviesListState {
    val items: List<MovieShortEntity>
    val query: String
    val isEmpty: Boolean
    val isLoading: Boolean
    val error: String?
    val selectedTypes: Set<MovieType>
    val showTypesDialog: Boolean
    val hasBadge: Boolean
    val typesVariants: Set<MovieType>
}