package com.example.data.repository

import com.example.data.repository.datasource.LocalDataSource
import com.example.data.repository.datasource.RemoteDataSource
import com.example.domain.repository.NewsRepository

class NewsRepositoryImpl(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : NewsRepository {
    override fun getNews() = remoteDataSource.getNews()
    override fun getNewsFromDb(id: Int) = localDataSource.getNewsFromDb(id)
}