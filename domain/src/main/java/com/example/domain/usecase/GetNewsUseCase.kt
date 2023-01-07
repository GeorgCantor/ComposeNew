package com.example.domain.usecase

import com.example.domain.repository.NewsRepository

class GetNewsUseCase(private val repository: NewsRepository) {
    operator fun invoke() = repository.getNews()
}