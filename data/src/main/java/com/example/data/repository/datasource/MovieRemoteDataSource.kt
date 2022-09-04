package com.example.data.repository.datasource

import androidx.paging.PagingData
import com.example.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRemoteDataSource {
    fun getPopularMovies(): Flow<PagingData<Movie>>
}