package com.example.android.movies.app.views.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.android.movies.R

@Composable
fun HomeTopBar(){
    val context = LocalContext.current.applicationContext
    Surface(
        shadowElevation = 9.dp,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(0.dp, 0.dp, 8.dp, 8.dp))
                .background(colorResource(id = R.color.colorPrimary))
        ) {
            Row(
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Spacer(Modifier.weight(0.7f))
                Image(
                    modifier = Modifier.size(60.dp),
                    painter = painterResource(id = R.mipmap.ic_launcher_foreground),
                    alignment = Alignment.CenterStart,
                    contentDescription = ""
                )
                Text(
                    text = context.getString(R.string.title_home),
                    style = MaterialTheme.typography.displayMedium,
                    color = colorResource(id = R.color.white),
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Medium
                )
                Spacer(Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun ExtendedFloatingActionButtonSearch(searchAction: () -> Unit) {
    val context = LocalContext.current.applicationContext
    Box(
        modifier = Modifier
            .fillMaxSize()
    ){
        ExtendedFloatingActionButton(
            modifier = Modifier
                .padding(all = 8.dp)
                .align(alignment = Alignment.BottomEnd),

            contentColor = colorResource(id = R.color.white),
            containerColor = colorResource(id = R.color.colorAccent),
            text = { Text(
                text = context.getString(R.string.search_movie),
                color = colorResource(id = R.color.white)
            ) },
            onClick = { searchAction.invoke() },
            shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
            icon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = context.getString(R.string.search_movie)
                )
            })
    }
}