package com.example.domain.usecase

data class MovieUseCases(
    val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    val getMoviesFromDbUseCase: GetMoviesFromDbUseCase
)