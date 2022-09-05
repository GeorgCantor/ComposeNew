package com.example.domain.usecase

import com.example.domain.repository.MovieRepository

class GetMoviesFromDbUseCase(private val repository: MovieRepository) {
    operator fun invoke(movieId: Int) = repository.getMoviesFromDb(movieId)
}