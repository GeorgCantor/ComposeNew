package com.example.compose.di

import com.example.domain.repository.NewsRepository
import com.example.domain.usecase.GetNewsFromDbUseCase
import com.example.domain.usecase.GetNewsUseCase
import com.example.domain.usecase.NewsUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideNewsUseCases(repository: NewsRepository) = NewsUseCases(
        GetNewsUseCase(repository),
        GetNewsFromDbUseCase(repository)
    )
}