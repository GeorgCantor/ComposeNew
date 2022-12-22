package com.example.data.repository.datasource

import com.example.domain.model.New
import kotlinx.coroutines.flow.Flow

interface MovieLocalDataSource {
    fun getMoviesFromDb(movieId: Int): Flow<New>
}