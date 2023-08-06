package com.example.android.movies.app.views.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.movies.core.domain.entity.GenreEntity
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android.movies.R

@Composable
fun GenreItem(data: GenreEntity, action: (id: String, name: String) -> Unit) {
    Box(
        modifier = Modifier
            .padding(8.dp, 0.dp, 8.dp, 0.dp)
            .fillMaxWidth()
            .clickable (
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true),
            ){
                action(data.id.toString(), data.name)
            }
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
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
                modifier = Modifier.size(18.dp),
                painter = rememberVectorPainter(
                    image = ImageVector.vectorResource(
                        id = R.drawable.ic_chevron
                    )
                ),
                alignment = Alignment.CenterEnd,
                contentDescription = ""
            )
        }
        Divider(color = Color.LightGray, thickness = 0.5.dp)
    }
}

@Composable
fun GenreList(items: List<GenreEntity>, action: (id: String, name: String) -> Unit){
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = rememberLazyListState()
    ) {
        items(count = items.size) {index ->
            GenreItem(data = items[index]){ id, name ->
                action(id, name)
            }
        }
    }
}