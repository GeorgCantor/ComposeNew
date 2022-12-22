package com.example.compose.presentation.screen.details

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.compose.ui.theme.AppContentColor
import com.example.compose.ui.theme.AppThemeColor

@Composable
fun MovieDetailsScreen(
    movieId: String,
    navController: NavController,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
) {
    viewModel.getMovieDetails(movieId = movieId.toInt())
    val movie by viewModel.selectedNew.collectAsState()
    Scaffold(
        topBar = { MovieDetailsTopBar(navController) },
        contentColor = MaterialTheme.colors.AppContentColor,
        backgroundColor = MaterialTheme.colors.AppThemeColor,
        content = { movie?.let { MovieDetailsContent(it) } }
    )
}
