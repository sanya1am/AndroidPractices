package com.sanya1am.impl.data.model

import com.google.gson.annotations.SerializedName

data class MovieSearchResponse(
    @SerializedName("docs")
    val movies: List<MovieShortResponse>?
)

data class MovieShortResponse(
    val id: String?,
    val name: String?,
    val year: String?,
    val type: String?,
    val poster: MovieFullResponse.PosterResponse?
)