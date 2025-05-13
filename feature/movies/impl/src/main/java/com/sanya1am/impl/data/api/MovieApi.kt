package com.sanya1am.impl.data.api

import com.sanya1am.impl.data.model.MovieFullResponse
import com.sanya1am.impl.data.model.MovieSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {
    @GET("movie/search")
    suspend fun getMovies(
        @Query("query") query: String = "",
        @Query("limit") limit: String = "70",
        @Query("status") status: String = "completed",
    ) : MovieSearchResponse

    @GET("movie/{id}")
    suspend fun getMovie(
        @Path("id") id: String
    ) : MovieFullResponse
}