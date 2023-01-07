package com.example.compose.di

import com.example.data.repository.NewsRepositoryImpl
import com.example.data.repository.datasource.LocalDataSource
import com.example.data.repository.datasource.RemoteDataSource
import com.example.domain.repository.NewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideNewsRepository(
        remoteDataSource: RemoteDataSource,
        localDataSource: LocalDataSource
    ): NewsRepository = NewsRepositoryImpl(remoteDataSource, localDataSource)
}