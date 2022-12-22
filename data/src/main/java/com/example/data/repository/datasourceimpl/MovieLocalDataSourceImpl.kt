package com.example.data.repository.datasourceimpl

import com.example.data.db.NewsDao
import com.example.data.repository.datasource.MovieLocalDataSource

class MovieLocalDataSourceImpl(private val newsDao: NewsDao) : MovieLocalDataSource {
    override fun getMoviesFromDb(movieId: Int) = newsDao.getNew(movieId)
}