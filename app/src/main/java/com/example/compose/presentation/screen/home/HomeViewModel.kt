package com.example.compose.presentation.screen.home

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(useCase: NewsUseCases) : ViewModel() {
    val getAllNews = useCase.getNewsUseCase()
}