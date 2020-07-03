package com.example.android.movies.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.movies.R
import com.example.android.movies.databinding.ActivityMovieListBinding
import com.example.android.movies.viewmodels.MovieListViewModel
import com.example.android.movies.views.adapters.MovieListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class MovieListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieListBinding
    private val movieListViewModel: MovieListViewModel by viewModel<MovieListViewModel>()
    private lateinit var adapter: MovieListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupUI()

        movieListViewModel.loadMoviesByGenre(genreId)
    }

    private fun setupObservers() {
        movieListViewModel.movieList.observe(this, Observer {
            binding.layoutEmpty.root.visibility = View.GONE
            binding.layoutError.root.visibility = View.GONE
            adapter.update(it)
        })

        movieListViewModel.isViewLoading.observe(this, Observer {
            val visibility = if(it) View.VISIBLE else View.GONE
            binding.loader.visibility = visibility
        })

        movieListViewModel.isEmptyList.observe(this, Observer {
            if(it){
                binding.layoutEmpty.root.visibility = View.VISIBLE
                binding.layoutError.root.visibility = View.GONE
            }else{
                binding.layoutEmpty.root.visibility = View.GONE
            }
        })

        movieListViewModel.onMessageError.observe(this, Observer {
            binding.layoutError.root.visibility = View.VISIBLE
            binding.layoutEmpty.root.visibility = View.GONE
        })
    }

    private fun setupUI() {
        adapter = MovieListAdapter(movieListViewModel.movieList.value!!)
        binding.rvMovies.layoutManager = LinearLayoutManager(this)
        binding.rvMovies.adapter = adapter

        binding.include.tvHeader.text = genreName
    }

    companion object{
        lateinit var genreId: String
        lateinit var genreName: String
        fun newInstance(context: Context, genreId: String, genreName: String): Intent{
            this.genreId = genreId
            this.genreName = genreName
            return Intent(context, MovieListActivity::class.java)
        }
    }
}
