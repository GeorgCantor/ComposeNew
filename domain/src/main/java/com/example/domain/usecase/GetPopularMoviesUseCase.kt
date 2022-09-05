package com.example.domain.usecase

import com.example.domain.repository.MovieRepository

class GetPopularMoviesUseCase(private val repository: MovieRepository) {
    operator fun invoke() = repository.getPopularMovies()
}