package com.example.data.repository.datasource

import com.example.domain.model.New
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getNewsFromDb(id: Int): Flow<New>
}