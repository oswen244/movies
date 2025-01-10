package com.example.android.movies.app.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.example.android.movies.R
import com.example.android.movies.app.viewmodels.MovieDetailViewModel
import com.example.android.movies.app.views.composables.OverViewMovie
import com.example.android.movies.app.views.composables.RateComponent
import com.example.android.movies.app.views.composables.TopAppBarTransparent
import com.example.android.movies.app.views.ui.theme.Dimens.DP_8
import com.example.android.movies.app.views.ui.theme.MoviesTheme
import com.example.android.movies.utils.Methods.formatDate
import com.example.android.movies.utils.Methods.getRate
import org.koin.androidx.viewmodel.ext.android.viewModel

const val MOVIE_ID = "movie_id"
const val MOVIE_NAME = "movie_name"

@OptIn(ExperimentalGlideComposeApi::class)
class MovieDetailV2Activity : ComponentActivity() {

    private val movieDetailViewModel: MovieDetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MoviesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MovieDetailView()
                }
            }
        }
        getMovieDetail()
    }

    private fun getMovieDetail() {
        intent.getIntExtra(MOVIE_ID, 0).let {
            movieDetailViewModel.loadMovieDetail(it)
        }
    }


    @Composable
    fun MovieDetailView(){
        val movieName = intent.getStringExtra(MOVIE_NAME).orEmpty()
        val image = movieDetailViewModel.movieImage.observeAsState()
        val rate = movieDetailViewModel.movieRate.observeAsState()
        val releaseDate = movieDetailViewModel.movieReleaseDate.observeAsState()
        val overview = movieDetailViewModel.movieOverview.observeAsState()
        val url = movieDetailViewModel.urlHomepage.observeAsState()
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ){
                    GlideImage(
                        contentScale = ContentScale.FillWidth,
                        contentDescription = null,
                        model = image.value
                    ){
                        it.error(R.drawable.ic_photo_camera)
                            .placeholder(R.drawable.ic_photo_camera)
                            .load(image.value)
                    }
                    RateComponent(
                        modifier = Modifier
                            .padding(DP_8)
                            .align(Alignment.BottomEnd),
                        rate = getRate(rate.value.orEmpty())
                    )
                }
                OverViewMovie(
                    modifier = Modifier.fillMaxWidth()
                        .padding(DP_8),
                    releaseData = formatDate(releaseDate.value.orEmpty()),
                    overView = overview.value.orEmpty(),
                    url = url.value.orEmpty()
                )
            }
            TopAppBarTransparent(
                title = movieName,
                action = { finish() }
            )
        }
    }

    companion object{
        fun newInstance(context: Context, movieName: String?, movieId: Int?): Intent {
            return Intent(context, MovieDetailV2Activity::class.java).apply {
                putExtra(MOVIE_NAME, movieName)
                putExtra(MOVIE_ID, movieId)
            }
        }
    }
}