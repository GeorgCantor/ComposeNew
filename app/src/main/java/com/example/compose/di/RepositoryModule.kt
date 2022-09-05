package com.example.compose.di

import com.example.data.repository.MovieRepositoryImpl
import com.example.data.repository.datasource.MovieLocalDataSource
import com.example.data.repository.datasource.MovieRemoteDataSource
import com.example.domain.repository.MovieRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    fun provideMoviesRepository(
        remoteDataSource: MovieRemoteDataSource,
        localDataSource: MovieLocalDataSource
    ): MovieRepository = MovieRepositoryImpl(remoteDataSource, localDataSource)
}