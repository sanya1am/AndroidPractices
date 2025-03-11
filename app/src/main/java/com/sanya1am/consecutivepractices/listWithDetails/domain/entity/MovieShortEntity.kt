package com.sanya1am.consecutivepractices.listWithDetails.domain.entity

import androidx.annotation.StringRes
import com.sanya1am.consecutivepractices.R
class MovieShortEntity (
    val id: String,
    val name: String,
    val year: String,
    val type: MovieType,
    val rating: MovieFullEntity.RatingEntity,
    val posterPreviewUrl: String
)

enum class MovieType(@StringRes val stringRes: Int) {
    MOVIE(R.string.movie),
    TV_SERIES(R.string.tv_series),
    ANIME(R.string.anime)
}