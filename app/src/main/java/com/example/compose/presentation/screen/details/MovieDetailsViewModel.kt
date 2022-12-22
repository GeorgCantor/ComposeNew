package com.example.compose.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.New
import com.example.domain.usecase.MovieUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val movieUseCases: MovieUseCases
) : ViewModel() {
    private val _selectedNew: MutableStateFlow<New?> = MutableStateFlow(null)
    val selectedNew: StateFlow<New?> = _selectedNew

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            movieUseCases.getMoviesFromDbUseCase.invoke(movieId = movieId).collect {
                _selectedNew.value = it
            }
        }
    }
}