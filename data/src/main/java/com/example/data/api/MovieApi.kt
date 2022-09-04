package com.example.data.api

import com.example.domain.model.MovieList
import retrofit2.Response
import retrofit2.http.Query

interface MovieApi {
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int = 1
    ): Response<MovieList>
}