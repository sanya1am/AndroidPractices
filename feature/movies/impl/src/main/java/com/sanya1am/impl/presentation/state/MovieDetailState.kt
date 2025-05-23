package com.sanya1am.impl.presentation.state

import com.sanya1am.api.domain.entity.MovieFullEntity

interface MovieDetailState {
    val movie: MovieFullEntity?
    val rating: Float
    val isLoading: Boolean
    val error: String?
}