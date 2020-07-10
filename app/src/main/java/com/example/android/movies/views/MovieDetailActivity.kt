package com.example.android.movies.views

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.example.android.movies.databinding.ActivityMovieDetailBinding
import com.example.android.movies.utils.Methods
import com.example.android.movies.viewmodels.MovieDetailViewModel
import com.example.android.movies.views.adapters.MovieCastAdapter
import com.example.android.movies.views.adapters.ProducersAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private val movieDetailViewModel: MovieDetailViewModel by viewModel<MovieDetailViewModel>()
    private lateinit var producerAdapter: ProducersAdapter
    private lateinit var castAdapter: MovieCastAdapter

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

        producerAdapter = ProducersAdapter(movieDetailViewModel.movieProducers.value!!)
        binding.rvProducers.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvProducers.adapter = producerAdapter

        castAdapter = MovieCastAdapter(movieDetailViewModel.movieCast.value!!)
        binding.rvCast.layoutManager = LinearLayoutManager(this)
        binding.rvCast.adapter = castAdapter
    }

    private fun setupObservers() {

        movieDetailViewModel.movieImage.observe(this, Observer {
            it?.let {
                Glide.with(this).load(it).listener(object :
                    RequestListener<Drawable> {
                    override fun onLoadFailed(p0: GlideException?, p1: Any?, p2: com.bumptech.glide.request.target.Target<Drawable>?, p3: Boolean): Boolean {
                        binding.ivMovieImageBackground.visibility = View.GONE
                        binding.rlLoadingImage.visibility = View.VISIBLE
                        return false
                    }
                    override fun onResourceReady(p0: Drawable?, p1: Any?, p2: com.bumptech.glide.request.target.Target<Drawable>?, p3: DataSource?, p4: Boolean): Boolean {
                        binding.rlLoadingImage.visibility = View.GONE
                        binding.ivMovieImageBackground.visibility = View.VISIBLE
                        return false
                    }
                }).into(binding.ivMovieImageBackground)
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

        movieDetailViewModel.movieReleaseDate.observe(this, Observer {
            it?.let {
                binding.tvReleaseDate.text = String.format("Released date: %s", Methods.formatDate(it))
            }
        })

        movieDetailViewModel.movieProducers.observe(this, Observer {
            producerAdapter.updateList(it)
        })

        movieDetailViewModel.movieCast.observe(this, Observer {
            castAdapter.updateList(it)
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
