package com.example.compose.di

import com.example.domain.repository.MovieRepository
import com.example.domain.usecase.GetMoviesFromDbUseCase
import com.example.domain.usecase.GetPopularMoviesUseCase
import com.example.domain.usecase.MovieUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideMovieUseCases(repository: MovieRepository) = MovieUseCases(
        GetPopularMoviesUseCase(repository),
        GetMoviesFromDbUseCase(repository)
    )
}