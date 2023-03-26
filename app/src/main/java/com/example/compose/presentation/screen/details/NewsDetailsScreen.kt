package com.example.compose.presentation.screen.details

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.compose.ui.theme.CollapsingToolbarTheme

@Composable
fun NewsDetailsScreen(
    id: String,
    navController: NavController,
    viewModel: NewsDetailsViewModel = hiltViewModel(),
) {
    viewModel.getNewsDetails(id = id.toInt())
    val new by viewModel.selectedNew.collectAsState()
    CollapsingToolbarTheme {
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            new?.let { CollapsingToolbarDetails(it, navController) }
        }
    }
}
