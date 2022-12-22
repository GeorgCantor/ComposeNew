package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.New
import kotlinx.coroutines.flow.Flow

interface MovieRepository {
    fun getPopularMovies(): Flow<PagingData<New>>
    fun getMoviesFromDb(movieId: Int): Flow<New>
}