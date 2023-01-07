package com.example.compose.di

import com.example.data.api.NewsApi
import com.example.data.db.NewsDatabase
import com.example.data.repository.datasource.RemoteDataSource
import com.example.data.repository.datasourceimpl.RemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    fun provideRemoteDataSource(api: NewsApi, db: NewsDatabase): RemoteDataSource = RemoteDataSourceImpl(api, db)
}