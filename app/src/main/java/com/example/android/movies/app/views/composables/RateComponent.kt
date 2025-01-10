package com.example.android.movies.app.views.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.android.movies.app.views.ui.theme.Dimens.DP_0
import com.example.android.movies.app.views.ui.theme.Dimens.DP_30
import com.example.android.movies.app.views.ui.theme.Dimens.DP_50
import com.example.android.movies.app.views.ui.theme.Dimens.DP_70
import com.example.android.movies.app.views.ui.theme.Dimens.DP_8
import com.example.android.movies.app.views.ui.theme.Dimens.SP_14
import com.example.android.movies.app.views.ui.theme.colorBlackTranslucent

@Composable
fun RateComponent(
    modifier: Modifier,
    rate: String
){
    Box(modifier = modifier){
        Row(
            modifier = Modifier
                .width(DP_70)
                .height(DP_30)
                .background(
                    color = colorBlackTranslucent,
                    shape = RoundedCornerShape(DP_50)
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.padding(DP_8, DP_0, DP_0, DP_0),
                text = rate,
                color = Color.White,
                fontSize = SP_14,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Medium,
            )
            Icon(
                modifier = Modifier.padding(DP_8, DP_0),
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                tint = Color.Yellow
            )
        }
    }
}

@Preview
@Composable
fun RateComponentPreview(){
    RateComponent(Modifier, "5.0")
}