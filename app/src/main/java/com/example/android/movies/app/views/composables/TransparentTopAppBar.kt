package com.example.android.movies.app.views.composables

import androidx.compose.material.AppBarDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp
import com.example.android.movies.R

@Composable
fun TopAppBarTransparent(
    title: String,
    modifier: Modifier = Modifier,
    action: () -> Unit,
    backgroundColor: Color = colorResource(id = R.color.colorBlackTranslucent),
    elevation: Dp = AppBarDefaults.TopAppBarElevation
) {
    TopAppBar(
        modifier = modifier,
        elevation = elevation,
        title = {
            Text(
                text = title,
                color = colorResource(id = R.color.white),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        },
        backgroundColor =  backgroundColor,
        navigationIcon = {
            IconButton(onClick = { action.invoke() }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = colorResource(id = R.color.white))
            }
        }
    )
}