package com.sanya1am.consecutivepractices.listWithDetails.domain.entity

class MovieFullEntity(
    val id: String,
    val name: String,
    val type: MovieType,
    val year: String,
    val description: String = "",
    val shortDescription: String = "",
    val status: String,
    val rating: RatingEntity,
    val movieLength: String = "",
//    val totalSeriesLength: String = "",
//    val seriesLength: String?,
    val ageRating: String = "",
    val poster: PosterEntity,
    val genres: List<String>,
    val countries: List<String>,
) {
    class RatingEntity(
        val kp: String,
        val imdb: String,
    )
    class PosterEntity(
        val url: String,
        val previewUrl: String,
    )
}


