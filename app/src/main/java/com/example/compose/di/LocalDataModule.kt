package com.example.compose.di

import com.example.data.db.MovieDao
import com.example.data.repository.datasource.MovieLocalDataSource
import com.example.data.repository.datasourceimpl.MovieLocalDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    fun provideLocalDataSource(movieDao: MovieDao): MovieLocalDataSource = MovieLocalDataSourceImpl(movieDao)
}