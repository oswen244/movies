package com.example.android.movies.app.views.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android.movies.R

@Composable
fun RateComponent(
    modifier: Modifier,
    rate: String
){
    Box(modifier = modifier){
        Row(
            modifier = Modifier
                .width(70.dp)
                .height(30.dp)
                .background(
                    color = colorResource(id = R.color.colorBlackTranslucent),
                    shape = RoundedCornerShape(50.dp)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(8.dp, 0.dp, 0.dp, 0.dp),
                text = rate,
                color = colorResource(id = R.color.white),
                fontSize = 14.sp,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Medium,
            )
            Icon(
                modifier = Modifier.padding(8.dp, 0.dp),
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = Color.Yellow
            )
        }
    }
}