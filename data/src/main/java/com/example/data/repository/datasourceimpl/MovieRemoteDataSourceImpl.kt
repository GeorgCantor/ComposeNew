package com.example.data.repository.datasourceimpl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.data.api.MovieApi
import com.example.data.db.MovieDatabase
import com.example.data.paging.MovieRemoteMediator
import com.example.data.repository.datasource.MovieRemoteDataSource
import com.example.domain.model.Movie
import kotlinx.coroutines.flow.Flow

class MovieRemoteDataSourceImpl(
    private val api: MovieApi,
    private val db: MovieDatabase
) : MovieRemoteDataSource {
    private val dao = db.movieDao()

    @OptIn(ExperimentalPagingApi::class)
    override fun getPopularMovies(): Flow<PagingData<Movie>> = Pager(
        config = PagingConfig(20),
        remoteMediator = MovieRemoteMediator(api, db),
        pagingSourceFactory = { dao.getAllMovies() }
    ).flow
}