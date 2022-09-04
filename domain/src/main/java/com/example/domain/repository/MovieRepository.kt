package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<PagingData<Movie>>
    fun getMoviesFromDb(movieId: Int): Flow<Movie>
}