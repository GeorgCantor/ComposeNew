package com.example.domain.usecase

data class NewsUseCases(
    val getNewsUseCase: GetNewsUseCase,
    val getNewsFromDbUseCase: GetNewsFromDbUseCase
)