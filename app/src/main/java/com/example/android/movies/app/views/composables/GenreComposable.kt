package com.example.android.movies.app.views.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.movies.core.domain.entity.GenreEntity
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.android.movies.R

@Composable
fun GenreItem(data: GenreEntity, action: (id: String, name: String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { action(data.id.toString(), data.name) }
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = data.name,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black,
                fontWeight = FontWeight.Medium
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