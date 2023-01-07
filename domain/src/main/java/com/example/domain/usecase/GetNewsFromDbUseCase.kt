package com.example.domain.usecase

import com.example.domain.repository.NewsRepository

class GetNewsFromDbUseCase(private val repository: NewsRepository) {
    operator fun invoke(id: Int) = repository.getNewsFromDb(id)
}