package com.sanya1am.consecutivepractices.listWithDetails.data.repository

import com.sanya1am.consecutivepractices.listWithDetails.data.api.MovieApi
import com.sanya1am.consecutivepractices.listWithDetails.data.mapper.MovieResponseToEntityMapper
import com.sanya1am.consecutivepractices.listWithDetails.domain.entity.MovieFullEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MoviesRepository(
    private val api: MovieApi,
    private val mapper: MovieResponseToEntityMapper
) {
    suspend fun getList(q: String) =
        withContext(Dispatchers.IO) {
            val response = api.getMovies(q)
            mapper.mapToShortsEntity(response)
        }

    suspend fun getById(id: String): MovieFullEntity =
        withContext(Dispatchers.IO) {
            val response = api.getMovie(id)
            mapper.mapToFullEntity(response)
        }
}