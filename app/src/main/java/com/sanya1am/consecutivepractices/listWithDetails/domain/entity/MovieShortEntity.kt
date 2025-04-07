package com.sanya1am.consecutivepractices.listWithDetails.domain.entity

import androidx.annotation.StringRes
import com.google.gson.annotations.SerializedName
import com.sanya1am.consecutivepractices.R
class MovieShortEntity (
    val id: String,
    val name: String,
    val year: String,
    val type: MovieType,
    val poster: MovieFullEntity.PosterEntity
)

enum class MovieType(@StringRes val stringRes: Int) {
    @SerializedName("movie") MOVIE(R.string.movie),
    @SerializedName("tv-series") TV_SERIES(R.string.tv_series),
    @SerializedName("cartoon") CARTOON(R.string.cartoon),
    @SerializedName("anime") ANIME(R.string.anime),
    @SerializedName("animated-series") ANIMATED_SERIES(R.string.animated_series),
    OTHER(R.string.other);

    companion object {
        fun getByValue(type: String?) = entries.find { it.name.equals(type?.replace("-", "_"), ignoreCase = true) } ?: OTHER
    }
}