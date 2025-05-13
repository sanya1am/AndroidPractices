package com.sanya1am.impl.data.mapper

import com.sanya1am.impl.data.model.MovieFullResponse
import com.sanya1am.impl.data.model.MovieSearchResponse
import com.sanya1am.api.domain.entity.MovieFullEntity
import com.sanya1am.api.domain.entity.MovieShortEntity
import com.sanya1am.api.domain.entity.MovieType

class MovieResponseToEntityMapper {
    fun mapToFullEntity(response: MovieFullResponse) : MovieFullEntity {
        return MovieFullEntity(
            id = response.id.orEmpty(),
            name = response.name.orEmpty(),
            type = MovieType.getByValue(response.type) ?: MovieType.MOVIE,
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
            url = response.poster?.url.orEmpty(),
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
                type = MovieType.getByValue(movie.type) ?: MovieType.MOVIE,
                previewUrl = movie.poster?.previewUrl.orEmpty()
            )
        }.orEmpty()
    }
}