package com.example.compose.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.example.compose.presentation.navigation.Screen
import com.example.compose.ui.theme.ItemBackgroundColor
import com.example.domain.model.New

@Composable
fun NewsListContent(allNews: LazyPagingItems<New>, navController: NavHostController, res: Int) {
    LazyColumn(contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp)) {
        items(
            items = allNews,
            key = { it.pk }
        ) { new -> new?.let { NewsItem(aNew = new, navController = navController, res = res) } }
    }
}

@Composable
fun NewsItem(aNew: New, navController: NavHostController?, res: Int?) {
    Card(
        modifier = Modifier
            .padding(top = 8.dp)
            .height(180.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        backgroundColor = MaterialTheme.colors.ItemBackgroundColor
    ) {
        Row(
            modifier = Modifier
                .height(IntrinsicSize.Max)
                .fillMaxWidth()
                .clickable {
                    navController?.navigate(route = Screen.NewsDetails.passId(aNew.id.toString()))
                },
            verticalAlignment = Alignment.CenterVertically
        ) {
            aNew.posterPath?.let {
                Image(
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .height(160.dp)
                        .width(120.dp)
                        .clip(RoundedCornerShape(4.dp)),
                    painter = rememberImagePainter(
                        data = aNew.posterPath, builder = {
                            crossfade(true)
                            scale(Scale.FILL)
                            res?.let { placeholder(it) }
                        }
                    ),
                    contentDescription = null,
                    contentScale = ContentScale.Crop
                )
            }
            Column(
                Modifier
                    .height(IntrinsicSize.Max)
                    .padding(horizontal = 8.dp, vertical = 10.dp)
                    .align(Alignment.Top)
            ) {
                aNew.title?.let {
                    Text(
                        text = it,
                        maxLines = 3,
                        style = MaterialTheme.typography.body1
                    )
                }
                Spacer(modifier = Modifier.height(4.dp))
                aNew.overview?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.body2,
                        maxLines = 8,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun NewsItemPreview() {
    NewsItem(
        aNew = New(
            pk = 0,
            id = 1,
            overview = "To set rounded corners for Image in Android Compose, apply clip modifier with RoundedCornerShape. Pass required corner radius in dp, float, or int (percentage) to RoundedCornerShape().",
            posterPath = "",
            title = "Title",
            releaseDate = "20.09.2020",
        ),
        navController = null,
        res = null
    )
}