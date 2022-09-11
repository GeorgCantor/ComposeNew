package com.example.compose.presentation.screen.home

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.compose.ui.theme.AppContentColor
import com.example.compose.ui.theme.AppThemeColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun HomeScreen(navController: NavHostController, viewModel: HomeViewModel = hiltViewModel()) {
    val systemUiController = rememberSystemUiController()
    val systemBarColor = MaterialTheme.colors.AppThemeColor
    val allMovies = viewModel.getAllPopularMovies.collectAsLazyPagingItems()

    SideEffect { systemUiController.setStatusBarColor(color = systemBarColor) }
    Scaffold(
        backgroundColor = MaterialTheme.colors.AppThemeColor,
        contentColor = MaterialTheme.colors.AppContentColor,
        topBar = { HomeTopBar() },
        content = { MovieListContent(allMovies = allMovies, navController = navController) }
    )
}