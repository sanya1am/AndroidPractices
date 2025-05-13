package com.sanya1am.impl.data.model

data class MovieFullResponse(
    val id: String?,
    val name: String?,
    val type: String?,
    val year: String?,
    val description: String?,
    val shortDescription: String?,
    val status: String?,
    val rating: RatingResponse?,
    val movieLength: String?,
//    val seriesLength: Int?,
    val ageRating: String?,
    val poster: PosterResponse?,
    val genres: List<GenreResponse>?,
    val countries: List<CountryResponse>?,
) {
    data class RatingResponse(
        val kp: String,
        val imdb: String,
    )
    data class PosterResponse(
        val url: String,
        val previewUrl: String
    )
    data class GenreResponse(val name: String)
    data class CountryResponse(val name: String)
}
