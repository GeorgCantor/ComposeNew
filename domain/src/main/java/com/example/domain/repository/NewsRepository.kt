package com.example.domain.repository

import androidx.paging.PagingData
import com.example.domain.model.New
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(): Flow<PagingData<New>>
    fun getNewsFromDb(id: Int): Flow<New>
}