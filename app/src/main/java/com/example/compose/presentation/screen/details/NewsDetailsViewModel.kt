package com.example.compose.presentation.screen.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.New
import com.example.domain.usecase.NewsUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsDetailsViewModel @Inject constructor(
    private val newsUseCases: NewsUseCases
) : ViewModel() {
    private val _selectedNew: MutableStateFlow<New?> = MutableStateFlow(null)
    val selectedNew: StateFlow<New?> = _selectedNew

    fun getNewsDetails(id: Int) {
        viewModelScope.launch {
            newsUseCases.getNewsFromDbUseCase.invoke(id = id).collect {
                _selectedNew.value = it
            }
        }
    }
}