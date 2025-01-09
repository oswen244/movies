package com.example.android.movies.app.views.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android.movies.R

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
               modifier = Modifier.padding(8.dp),
               text = "Overview",
               fontFamily = FontFamily.SansSerif,
               fontWeight = FontWeight.Medium,
               fontSize = 20.sp,
               color = colorResource(id = R.color.colorBlackText)
           )
           Text(
               modifier = Modifier.padding(8.dp),
               text = releaseData,
               fontFamily = FontFamily.SansSerif,
               fontWeight = FontWeight.Light,
               fontSize = 16.sp,
               color = colorResource(id = R.color.colorBlackText)
           )
        }
        Text(
            modifier = Modifier.padding(8.dp),
            text = overView
        )
        LinkText(
            modifier = Modifier.padding(8.dp, 16.dp),
            urlLink = url
        )
/*        Text(
            modifier = Modifier.padding(8.dp, 16.dp),
            text = "https://homepage.com"
        )*/
    }
}