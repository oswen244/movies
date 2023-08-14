package com.example.android.movies.app.views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.android.movies.R
import com.example.android.movies.app.model.state.GenresState
import com.example.android.movies.app.viewmodels.GenreViewModel
import com.example.android.movies.app.views.composables.CircularProgressBar
import com.example.android.movies.app.views.composables.ExtendedFloatingActionButtonSearch
import com.example.android.movies.app.views.composables.GenreItem
import com.example.android.movies.app.views.composables.HomeTopBar
import com.example.android.movies.app.views.ui.theme.MoviesTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val genreViewModel: GenreViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoviesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    color = MaterialTheme.colors.background
                ) {
                    Column {
                        HomeTopBar()
                        GetGenreList()
                    }
                    ExtendedFloatingActionButtonSearch {
                        openMovieSearch()
                    }
                }
            }
        }
        genreViewModel.loadMovieGenres()
    }

    @Composable
    fun GetGenreList(){
        val list = genreViewModel.genreList.observeAsState()
        val loading = genreViewModel.state.observeAsState()
        list.value?.let {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    state = rememberLazyListState(),
                    content = {
                        items(count = it.size) {index ->
                            GenreItem(data = it[index]){ id, name ->
                                openListByGenre(id, name)
                            }
                        }
                    }
                )
                CircularProgressBar(isDisplayed = loading.value is GenresState.LoadingState)
            }
        }
    }

    private fun openListByGenre(genreId: String, genreName: String){
        val intent = MovieListActivity.newInstance(this, genreId, genreName)
        startActivity(intent)
        overridePendingTransition(R.anim.enter, R.anim.exit)
    }

    private fun openMovieSearch(){
        val intent = SearchMovieActivity.newInstance(this)
        startActivity(intent)
    }
}