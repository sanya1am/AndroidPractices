package com.sanya1am.impl.data.repository

import com.sanya1am.impl.data.api.MovieApi
import com.sanya1am.impl.data.db.MovieDatabase
import com.sanya1am.impl.data.mapper.MovieResponseToEntityMapper
import com.sanya1am.impl.data.model.MovieDbEntity
import com.sanya1am.api.domain.entity.MovieFullEntity
import com.sanya1am.api.domain.entity.MovieShortEntity
import com.sanya1am.api.domain.entity.MovieType
import com.sanya1am.api.domain.repository.IMoviesRepositoty
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(
    private val api: MovieApi,
    private val mapper: MovieResponseToEntityMapper,
    private val db: MovieDatabase
) : IMoviesRepositoty {
    override suspend fun getList(q: String) =
        withContext(Dispatchers.IO) {
            val response = api.getMovies(
                query = q
            )
            mapper.mapToShortsEntity(response)
        }

    override suspend fun getById(id: String): MovieFullEntity =
        withContext(Dispatchers.IO) {
            val response = api.getMovie(id)
            mapper.mapToFullEntity(response)
        }

    override suspend fun saveFavorite(movie: MovieShortEntity) =
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

    override suspend fun getFavorites() =
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