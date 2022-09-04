package com.example.data.repository.datasourceimpl

import com.example.data.db.MovieDao
import com.example.data.repository.datasource.MovieLocalDataSource

class MovieLocalDataSourceImpl(private val movieDao: MovieDao) : MovieLocalDataSource {
    override fun getMoviesFromDb(movieId: Int) = movieDao.getMovie(movieId)
}