package com.example.data.repository.datasourceimpl

import com.example.data.db.NewsDao
import com.example.data.repository.datasource.LocalDataSource

class LocalDataSourceImpl(private val newsDao: NewsDao) : LocalDataSource {
    override fun getNewsFromDb(id: Int) = newsDao.getNew(id)
}