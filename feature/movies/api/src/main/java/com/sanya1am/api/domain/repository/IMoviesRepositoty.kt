package com.sanya1am.api.domain.repository

import com.sanya1am.api.domain.entity.MovieFullEntity
import com.sanya1am.api.domain.entity.MovieShortEntity

interface IMoviesRepositoty {
    suspend fun getList(q: String) : List<MovieShortEntity>
    suspend fun getById(id: String) : MovieFullEntity?
    suspend fun saveFavorite(movie: MovieShortEntity)
    suspend fun getFavorites() : List<MovieShortEntity>
}