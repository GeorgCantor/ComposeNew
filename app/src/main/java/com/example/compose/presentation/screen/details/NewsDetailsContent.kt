package com.example.compose.presentation.screen.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.example.compose.presentation.components.ReleaseDateComponent
import com.example.compose.ui.theme.AppThemeColor
import com.example.domain.model.New

@Composable
fun NewsDetailsContent(aNew: New) {
    val scrollState = rememberScrollState()
    Card(
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.AppThemeColor
    ) {
        Column(modifier = Modifier.fillMaxWidth().verticalScroll(scrollState)) {
            Image(
                painter = rememberImagePainter(
                    data = aNew.posterPath,
                    builder = {
                        crossfade(true)
                        scale(Scale.FIT)
                    }
                ),
                contentDescription = null,
                modifier = Modifier.fillMaxWidth().height(350.dp),
                contentScale = ContentScale.FillWidth
            )
            Column(modifier = Modifier.padding(8.dp)) {
                Spacer(modifier = Modifier.height(16.dp))
                aNew.title?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.h5,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                aNew.releaseDate?.let { ReleaseDateComponent(releaseDate = it) }
                Spacer(modifier = Modifier.height(16.dp))
                aNew.overview?.let { Text(text = it, style = MaterialTheme.typography.body2) }
            }
        }
    }
}