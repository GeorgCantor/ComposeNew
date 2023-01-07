package com.example.data.repository.datasourceimpl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.api.NewsApi
import com.example.data.db.NewsDatabase
import com.example.data.paging.NewsRemoteMediator
import com.example.data.repository.datasource.RemoteDataSource
import com.example.domain.model.New
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val api: NewsApi,
    private val db: NewsDatabase
) : RemoteDataSource {
    private val dao = db.newsDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getNews(): Flow<PagingData<New>> = Pager(
        config = PagingConfig(20),
        remoteMediator = NewsRemoteMediator(api, db),
        pagingSourceFactory = { dao.getAllNews() }
    ).flow
}