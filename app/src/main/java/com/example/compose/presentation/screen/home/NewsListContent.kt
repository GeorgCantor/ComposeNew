package com.example.compose.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.example.compose.presentation.components.RatingComponent
import com.example.compose.presentation.navigation.Screen
import com.example.compose.ui.theme.ItemBackgroundColor
import com.example.domain.model.New

@Composable
fun NewsListContent(allNews: LazyPagingItems<New>, navController: NavHostController) {
    LazyColumn(contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)) {
        items(
            items = allNews,
            key = { it.pk }
        ) { new -> new?.let { NewsItem(aNew = new, navController = navController) } }
    }
}

@Composable
fun NewsItem(aNew: New, navController: NavHostController) {
    Card(
        modifier = Modifier.padding(top = 8.dp).height(180.dp).fillMaxWidth(),
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.ItemBackgroundColor
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .fillMaxWidth()
                .clickable {
                    navController.navigate(route = Screen.NewsDetails.passId(aNew.id.toString()))
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            aNew.posterPath?.let {
                Image(
                    modifier = Modifier.padding(end = 4.dp,).width(120.dp),
                    painter = rememberImagePainter(
                        data = aNew.posterPath, builder = {
                            crossfade(true)
                            scale(Scale.FILL)
                        }),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
            }
            Column(Modifier.height(IntrinsicSize.Max).padding(end = 2.dp)) {
                aNew.title?.let { Text(text = it, style = MaterialTheme.typography.body1) }
                Spacer(modifier = Modifier.height(4.dp))
                aNew.overview?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body2,
                        maxLines = 4,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                RatingComponent(rating = "5")
            }
        }
    }
}