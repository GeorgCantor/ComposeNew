package com.example.data.repository

import com.example.data.repository.datasource.MovieLocalDataSource
import com.example.data.repository.datasource.MovieRemoteDataSource
import com.example.domain.repository.MovieRepository

class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource
) : MovieRepository {
    override fun getPopularMovies() = remoteDataSource.getPopularMovies()
    override fun getMoviesFromDb(movieId: Int) = localDataSource.getMoviesFromDb(movieId)
}