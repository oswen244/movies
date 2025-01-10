package com.example.android.movies.app.views.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.android.movies.app.views.ui.theme.Dimens.DP_8
import com.example.android.movies.app.views.ui.theme.Dimens.DP_0
import com.example.android.movies.app.views.ui.theme.Dimens.DP_05
import com.example.android.movies.app.views.ui.theme.Dimens.DP_16
import com.example.android.movies.app.views.ui.theme.Dimens.DP_18
import com.example.android.movies.app.views.ui.theme.Dimens.DP_20
import com.movies.core.domain.entity.GenreEntity

@Composable
fun GenreItem(data: GenreEntity, action: (id: String, name: String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(DP_8, DP_0)
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
            ) {
                action(data.id.toString(), data.name)
            }
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(DP_16)
        ) {
            Text(
                text = data.name,
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                fontFamily = FontFamily.SansSerif,
                fontWeight = FontWeight.Light
            )
            Spacer(Modifier.weight(1f))
            Image(
                modifier = Modifier.size(DP_18),
                painter = rememberVectorPainter(
                    image = Icons.Default.KeyboardArrowRight
                ),
                alignment = Alignment.CenterEnd,
                contentDescription = ""
            )
        }
        Divider(color = Color.LightGray, thickness = DP_05)
    }
}

@Composable
fun CircularProgressBar(
    isDisplayed: Boolean,
) {
    if(isDisplayed){
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(DP_20)
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.Center),
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}