package com.example.compose.di

import com.example.data.api.NewsApi
import com.example.data.db.MovieDatabase
import com.example.data.repository.datasource.MovieRemoteDataSource
import com.example.data.repository.datasourceimpl.MovieRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataModule {
    @Provides
    fun provideMoviesRemoteDataSource(api: NewsApi, db: MovieDatabase): MovieRemoteDataSource = MovieRemoteDataSourceImpl(api, db)
}