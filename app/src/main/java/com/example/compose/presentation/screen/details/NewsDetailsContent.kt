package com.example.compose.presentation.screen.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.example.domain.model.New

private val headerHeight = 300.dp
private val toolbarHeight = 56.dp
private val paddingMedium = 16.dp
private val titlePaddingStart = 16.dp
private val titlePaddingEnd = 72.dp
private const val titleFontScaleStart = 1f
private const val titleFontScaleEnd = 0.66f

@Composable
fun CollapsingToolbarDetails(
    aNew: New,
    navController: NavController?
) {
    val scroll: ScrollState = rememberScrollState(0)
    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.toPx() }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Header(scroll, headerHeightPx, aNew.posterPath.orEmpty())
        Body(aNew.overview.orEmpty(), scroll)
        Toolbar(scroll, headerHeightPx, toolbarHeightPx, navController)
        Title(scroll, headerHeightPx, toolbarHeightPx, aNew.title.orEmpty())
    }
}

@Composable
private fun Header(scroll: ScrollState, headerHeightPx: Float, posterPath: String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(headerHeight)
        .graphicsLayer {
            translationY = -scroll.value.toFloat() / 2f
            alpha = (-1f / headerHeightPx) * scroll.value + 1
        }
    ) {
        Image(
            modifier = Modifier.fillMaxWidth(),
            painter = rememberImagePainter(
                data = posterPath,
                builder = {
                    crossfade(true)
                    scale(Scale.FIT)
                }
            ),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )

        Box(
            Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xAA000000)),
                        startY = 3 * headerHeightPx / 4
                    )
                )
        )
    }
}

@Composable
private fun Body(overview: String, scroll: ScrollState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.verticalScroll(scroll)
    ) {
        Spacer(Modifier.height(headerHeight))
        Text(
            text = overview,
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .background(Color(0XFF161616))
                .padding(16.dp)
        )
        Spacer(Modifier.height(600.dp))
    }
}

@Composable
private fun Toolbar(
    scroll: ScrollState,
    headerHeightPx: Float,
    toolbarHeightPx: Float,
    navController: NavController?
) {
    val toolbarBottom = headerHeightPx - toolbarHeightPx
    val showToolbar by remember {
        derivedStateOf {
            scroll.value >= toolbarBottom
        }
    }

    AnimatedVisibility(
        visible = showToolbar,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300))
    ) {
        TopAppBar(
            modifier = Modifier.background(
                brush = Brush.horizontalGradient(
                    listOf(Color.DarkGray, Color.Black)
                )
            ),
            navigationIcon = {
                IconButton(
                    onClick = { navController?.popBackStack() },
                    modifier = Modifier
                        .padding(16.dp)
                        .size(24.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            },
            title = {},
            backgroundColor = Color.Transparent,
            elevation = 0.dp
        )
    }
}

@Composable
private fun Title(
    scroll: ScrollState,
    headerHeightPx: Float,
    toolbarHeightPx: Float,
    title: String
) {
    var titleHeightPx by remember { mutableStateOf(0f) }
    var titleWidthPx by remember { mutableStateOf(0f) }

    Text(
        text = title,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        modifier = Modifier
            .graphicsLayer {
                val collapseRange: Float = (headerHeightPx - toolbarHeightPx)
                val collapseFraction: Float = (scroll.value / collapseRange).coerceIn(0f, 1f)
                val scaleXY = lerp(
                    titleFontScaleStart.dp,
                    titleFontScaleEnd.dp,
                    collapseFraction
                )
                val titleExtraStartPadding = titleWidthPx.toDp() * (1 - scaleXY.value) / 2f
                val titleYFirstInterpolatedPoint = lerp(
                    headerHeight - titleHeightPx.toDp() - paddingMedium,
                    headerHeight / 2,
                    collapseFraction
                )
                val titleXFirstInterpolatedPoint = lerp(
                    titlePaddingStart,
                    (titlePaddingEnd - titleExtraStartPadding) * 5 / 4,
                    collapseFraction
                )
                val titleYSecondInterpolatedPoint = lerp(
                    headerHeight / 2,
                    toolbarHeight / 2 - titleHeightPx.toDp() / 2,
                    collapseFraction
                )
                val titleXSecondInterpolatedPoint = lerp(
                    (titlePaddingEnd - titleExtraStartPadding) * 5 / 4,
                    titlePaddingEnd - titleExtraStartPadding,
                    collapseFraction
                )
                val titleY = lerp(
                    titleYFirstInterpolatedPoint,
                    titleYSecondInterpolatedPoint,
                    collapseFraction
                )
                val titleX = lerp(
                    titleXFirstInterpolatedPoint,
                    titleXSecondInterpolatedPoint,
                    collapseFraction
                )
                translationY = titleY.toPx()
                translationX = titleX.toPx()
                scaleX = scaleXY.value
                scaleY = scaleXY.value
            }
            .onGloballyPositioned {
                titleHeightPx = it.size.height.toFloat()
                titleWidthPx = it.size.width.toFloat()
            }
    )
}

@Preview
@Composable
fun CollapsingToolbarDetailsPreview() {
    CollapsingToolbarDetails(
        aNew = New(
            pk = 0,
            id = 1,
            overview = "Animations are super important when building an application. They highlight relations between elements, bring attention to what is important and they also can express a brand’s style.\n" +
                    "\nOverall animations give a higher quality look and feel to your app and more importantly they are super cool to build as a developer \uD83D\uDE42",
            posterPath = "",
            title = "Title",
            releaseDate = "20.09.2020",
        ),
        navController = null
    )
}