package com.example.android.movies.app.views.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.android.movies.R
import com.example.android.movies.app.views.ui.theme.Dimens.DP_0
import com.example.android.movies.app.views.ui.theme.Dimens.DP_180
import com.example.android.movies.app.views.ui.theme.Dimens.DP_60
import com.example.android.movies.app.views.ui.theme.Dimens.DP_8
import com.example.android.movies.app.views.ui.theme.Dimens.DP_9
import com.example.android.movies.app.views.ui.theme.Purple500
import com.example.android.movies.app.views.ui.theme.accentColor

@Composable
fun HomeTopBar(title: String, imageId: Int){
    Surface(
        shadowElevation = DP_9,
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(DP_180)
                .clip(shape = RoundedCornerShape(DP_0, DP_0, DP_8, DP_8))
                .background(Purple500)
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Spacer(Modifier.weight(1f))
                Image(
                    modifier = Modifier.size(DP_60),
                    painter = painterResource(id = imageId),
                    alignment = Alignment.CenterStart,
                    contentDescription = ""
                )
                Spacer(Modifier.weight(1f))
                Text(
                    text = title,
                    style = MaterialTheme.typography.displayMedium,
                    color = Color.White,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun ExtendedFloatingActionButtonSearch(
    buttonText: String,
    searchAction: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        ExtendedFloatingActionButton(
            modifier = Modifier
                .padding(all = DP_8)
                .align(alignment = Alignment.BottomEnd),

            contentColor = Color.White,
            containerColor = accentColor,
            text = { Text(
                text = buttonText,
                color = Color.White
            ) },
            onClick = { searchAction.invoke() },
            shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = buttonText
                )
            })
    }
}

@Preview
@Composable
fun HomeTopBarPreview(){
    HomeTopBar("Movies", R.drawable.ic_round_movie)
}