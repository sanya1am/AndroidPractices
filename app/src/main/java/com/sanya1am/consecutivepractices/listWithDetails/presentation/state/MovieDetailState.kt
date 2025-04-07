package com.sanya1am.consecutivepractices.listWithDetails.presentation.state

import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieFullEntity
import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieShortEntity

interface MovieDetailState {
    val movie: MovieFullEntity?
    val rating: Float
    val isLoading: Boolean
    val error: String?
//    val related: List<MovieShortEntity>
}