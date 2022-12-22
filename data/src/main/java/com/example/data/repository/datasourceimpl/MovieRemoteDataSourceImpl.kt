package com.example.data.repository.datasourceimpl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.api.NewsApi
import com.example.data.db.NewsDatabase
import com.example.data.paging.MovieRemoteMediator
import com.example.data.repository.datasource.MovieRemoteDataSource
import com.example.domain.model.New
import kotlinx.coroutines.flow.Flow

class MovieRemoteDataSourceImpl(
    private val api: NewsApi,
    private val db: NewsDatabase
) : MovieRemoteDataSource {
    private val dao = db.newsDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getPopularMovies(): Flow<PagingData<New>> = Pager(
        config = PagingConfig(20),
        remoteMediator = MovieRemoteMediator(api, db),
        pagingSourceFactory = { dao.getAllNews() }
    ).flow
}