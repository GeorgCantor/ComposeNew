package com.example.data.repository.datasource

import androidx.paging.PagingData
import com.example.domain.model.New
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getNews(): Flow<PagingData<New>>
}