package com.sanya1am.consecutivepractices.listWithDetails.domain.entity

class MovieFullEntity(
    val id: String,
    val name: String,
    val alternativeName: String?,
    val type: MovieType,
    val year: String,
    val description: String?,
    val shortDescription: String?,
    val status: String,
    val rating: RatingEntity,
    val votes: VotesEntity,
    val movieLength: String?,
    val totalSeriesLength: String?,
    val seriesLength: String?,
    val ratingMpaa: String?,
    val ageRating: String?,
    val posterUrl: String,
    val posterPreviewUrl: String,
    val genres: List<String>,
    val countries: List<String>,
    val releaseYears: String,
    val isSeries: Boolean,
    val ticketsOnSale: Boolean
) {
    class RatingEntity(
        val kp: String,
        val imdb: String,
        val filmCritics: String,
        val russianFilmCritics: String,
        val await: String?
    )

    class VotesEntity(
        val kp: String,
        val imdb: String,
        val filmCritics: String,
        val russianFilmCritics: String,
        val await: String
    )
}


