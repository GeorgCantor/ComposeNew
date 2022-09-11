package com.example.compose.presentation.screen.home

import androidx.lifecycle.ViewModel
import com.example.domain.usecase.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(useCase: MovieUseCases) : ViewModel() {
    val getAllPopularMovies = useCase.getPopularMoviesUseCase()
}