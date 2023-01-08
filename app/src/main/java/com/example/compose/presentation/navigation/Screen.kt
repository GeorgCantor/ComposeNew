package com.example.compose.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home_screen")
    object NewsDetails : Screen("news_details_screen/{id}") {
        fun passId(id: String) = "news_details_screen/$id"
    }
}