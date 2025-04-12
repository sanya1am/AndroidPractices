package com.sanya1am.consecutivepractices.listWithDetails.data.mapper

import com.sanya1am.consecutivepractices.listWithDetails.data.model.MovieFullResponse
import com.sanya1am.consecutivepractices.listWithDetails.data.model.MovieSearchResponse
import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieFullEntity
import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieShortEntity
import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieType

class MovieResponseToEntityMapper {
    fun mapToFullEntity(response: MovieFullResponse) : MovieFullEntity {
        return MovieFullEntity(
            id = response.id.orEmpty(),
            name = response.name.orEmpty(),
            type = MovieType.getByValue(response.type),
            description = response.description.orEmpty(),
            year = response.year.orEmpty(),
            shortDescription = response.shortDescription.orEmpty(),
            status = response.status.orEmpty(),
            rating = MovieFullEntity.RatingEntity(
                kp = response.rating?.kp ?: "0.0",
                imdb = response.rating?.imdb ?: "0.0",
            ),
            movieLength = response.movieLength.orEmpty(),
            ageRating = response.ageRating.orEmpty(),
            poster = MovieFullEntity.PosterEntity(
                url = response.poster?.url ?: "",
                previewUrl = response.poster?.previewUrl ?: "",
            ),
            genres = response.genres?.map { it.name } ?: emptyList(),
            countries = response.countries?.map { it.name } ?: emptyList(),
        )
    }

    fun mapToShortsEntity(response: MovieSearchResponse): List<MovieShortEntity> {
        return response.movies?.map { movie ->
            MovieShortEntity(
                id = movie.id.orEmpty(),
                name = movie.name.orEmpty(),
                year = movie.year.orEmpty(),
                type = MovieType.getByValue(movie.type),
                poster = MovieFullEntity.PosterEntity(
                    url = movie.poster?.url.orEmpty(),
                    previewUrl = movie.poster?.previewUrl.orEmpty(),
                ),
            )
        }.orEmpty()
    }
}