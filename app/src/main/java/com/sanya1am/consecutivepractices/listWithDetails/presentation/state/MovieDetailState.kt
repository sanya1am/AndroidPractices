package com.sanya1am.consecutivepractices.listWithDetails.presentation.state

import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieFullEntity

interface MovieDetailState {
    val movie: MovieFullEntity?
    val rating: Float
}