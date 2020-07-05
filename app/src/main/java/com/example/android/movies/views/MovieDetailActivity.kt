package com.example.android.movies.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.android.movies.databinding.ActivityMovieDetailBinding
import com.example.android.movies.utils.Methods
import com.example.android.movies.viewmodels.MovieDetailViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private val movieDetailViewModel: MovieDetailViewModel by viewModel<MovieDetailViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupUI()

        movieDetailViewModel.loadMovieDetail(movieId)
    }

    private fun setupUI() {
        setSupportActionBar(binding.toolbarTransparent)
        Methods.setupToolbar(this, binding.toolbarTransparent, movieName, supportActionBar)
        binding.toolbarTransparent.setNavigationOnClickListener{
            finish()
        }
    }

    private fun setupObservers() {

        movieDetailViewModel.movieImage.observe(this, Observer {
            it?.let {
                Methods.imageUrl(this, it, binding.ivMovieImageBackground)
            }
        })

        movieDetailViewModel.movieOverview.observe(this, Observer {
            it?.let {
                binding.tvOverview.text = it
            }
        })

        movieDetailViewModel.movieRate.observe(this, Observer {
            binding.tvMovieRate.text = it ?: "0.0"
        })
    }

    companion object{
        lateinit var movieName: String
        var movieId: Int = 0
        fun newInstance(context: Context, movieName: String, movieId: Int): Intent {
            this.movieName = movieName
            this.movieId = movieId
            return Intent(context, MovieDetailActivity::class.java)
        }
    }
}
