package com.example.android.movies.app.views.composables

import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.example.android.movies.app.views.ui.theme.Dimens.DP_0
import com.example.android.movies.app.views.ui.theme.Dimens.SP_18
import com.example.android.movies.app.views.ui.theme.colorBlackTranslucent

@Composable
fun TopAppBarTransparent(
    title: String,
    modifier: Modifier = Modifier,
    action: () -> Unit,
    backgroundColor: Color = colorBlackTranslucent,
    elevation: Dp = DP_0
) {
    TopAppBar(
        modifier = modifier,
        elevation = elevation,
        title = {
            Text(
                text = title,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = SP_18,
                fontWeight = FontWeight.Medium
            )
        },
        backgroundColor =  backgroundColor,
        navigationIcon = {
            IconButton(onClick = { action.invoke() }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun TopAppBarTransparentPreview(){
    TopAppBarTransparent(
        "Wolvering and asshole",
        Modifier,
        action = {}
    )
}