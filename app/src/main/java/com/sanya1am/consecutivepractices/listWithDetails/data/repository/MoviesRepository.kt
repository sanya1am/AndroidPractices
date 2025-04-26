package com.sanya1am.consecutivepractices.listWithDetails.data.repository

import com.sanya1am.consecutivepractices.listWithDetails.data.api.MovieApi
import com.sanya1am.consecutivepractices.listWithDetails.data.db.MovieDatabase
import com.sanya1am.consecutivepractices.listWithDetails.data.mapper.MovieResponseToEntityMapper
import com.sanya1am.consecutivepractices.listWithDetails.data.model.MovieDbEntity
import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieFullEntity
import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieShortEntity
import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieType
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(
    private val api: MovieApi,
    private val mapper: MovieResponseToEntityMapper,
    private val db: MovieDatabase
) {
    suspend fun getList(q: String) =
        withContext(Dispatchers.IO) {
            val response = api.getMovies(
                query = q
            )
            mapper.mapToShortsEntity(response)
        }

    suspend fun getById(id: String): MovieFullEntity =
        withContext(Dispatchers.IO) {
            val response = api.getMovie(id)
            mapper.mapToFullEntity(response)
        }

    suspend fun saveFavorite(movie: MovieShortEntity) =
        withContext(Dispatchers.IO) {
            db.movieDao().insert(
                MovieDbEntity(
//                    id = movie.id.toLong(),
                    name = movie.name,
                    year = movie.year,
                    type = movie.type.name,
                    previewUrl = movie.previewUrl,
                )
            )
        }

    suspend fun getFavorites() =
        withContext(Dispatchers.IO) {
            db.movieDao().getAll().map {
                MovieShortEntity(
                    id = it.id.toString(),
                    name = it.name.orEmpty(),
                    year =  it.year.orEmpty(),
                    type =  MovieType.getByValue(it.type),
                    previewUrl = it.previewUrl.orEmpty()
                )
            }
        }
}