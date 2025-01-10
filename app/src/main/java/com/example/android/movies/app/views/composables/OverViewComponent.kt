package com.example.android.movies.app.views.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.android.movies.app.views.ui.theme.Dimens.DP_16
import com.example.android.movies.app.views.ui.theme.Dimens.DP_8
import com.example.android.movies.app.views.ui.theme.Dimens.SP_16
import com.example.android.movies.app.views.ui.theme.Dimens.SP_20
import com.example.android.movies.app.views.ui.theme.blackTextColor

@Composable
fun OverViewMovie(
    modifier: Modifier,
    releaseData: String,
    overView: String,
    url: String
){
    Column(
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ){
           Text(
               modifier = Modifier.padding(DP_8),
               text = "Overview",
               fontFamily = FontFamily.SansSerif,
               fontWeight = FontWeight.Medium,
               fontSize = SP_20,
               color = blackTextColor
           )
           Text(
               modifier = Modifier.padding(DP_8),
               text = releaseData,
               fontFamily = FontFamily.SansSerif,
               fontWeight = FontWeight.Light,
               fontSize = SP_16,
               color = blackTextColor
           )
        }
        Text(
            modifier = Modifier.padding(DP_8),
            text = overView
        )
        LinkText(
            modifier = Modifier.padding(DP_8, DP_16),
            urlLink = url
        )
    }
}