package com.example.compose.presentation.screen.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.example.compose.R
import com.example.compose.ui.theme.AppContentColor
import com.example.compose.ui.theme.AppThemeColor

@Composable
fun HomeTopBar() {
    val context = LocalContext.current
    TopAppBar(
        backgroundColor = MaterialTheme.colors.AppThemeColor,
        title = {
            Text(
                text = stringResource(R.string.app_name),
                color = MaterialTheme.colors.AppContentColor,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.h5
            )
        },
        elevation = 0.dp,
        actions = {
            IconButton(onClick = { showMessage(context = context) }) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorite",
                    tint = Color.Blue
                )
            }
        }
    )
}

fun showMessage(context: Context) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/GeorgCantor/ComposeNew"))
    ContextCompat.startActivity(context, intent, null)
}