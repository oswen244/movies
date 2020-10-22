package com.example.android.movies.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.LayoutAnimationController
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.movies.R
import com.example.android.movies.databinding.ActivityMovieListBinding
import com.example.android.movies.models.MovieList
import com.example.android.movies.utils.EndlessScrollViewListener
import com.example.android.movies.utils.ISortBy
import com.example.android.movies.utils.Methods
import com.example.android.movies.viewmodels.MovieListViewModel
import com.example.android.movies.views.adapters.MovieListAdapter
import com.example.android.movies.views.fragments.SortDialogFragment
import org.koin.android.viewmodel.ext.android.viewModel

class MovieListActivity : AppCompatActivity(), ISortBy {

    private lateinit var binding: ActivityMovieListBinding
    private val movieListViewModel: MovieListViewModel by viewModel<MovieListViewModel>()
    private lateinit var adapter: MovieListAdapter
    private lateinit var endlessScrollListener: EndlessScrollViewListener
    private var START_PAGE = 1
    private var currentPage = 0
    private lateinit var sortType: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupUI()
        sortType = getString(R.string.popularityDesc)
        movieListViewModel.loadMoviesByGenre(genreId, sortType,  START_PAGE)
    }

    private fun setupObservers() {
        movieListViewModel.currentPage.observe(this, Observer {
            currentPage = it
        })

        movieListViewModel.movieList.observe(this, Observer {
            binding.layoutEmpty.root.visibility = View.GONE
            binding.layoutError.root.visibility = View.GONE
            if(currentPage == START_PAGE){
                adapter.update(it as ArrayList<MovieList>)
            }else{
                adapter.addToList(it as ArrayList<MovieList>)
            }
        })

        movieListViewModel.isViewLoading.observe(this, Observer {
            val visibility = if(it) View.VISIBLE else View.GONE
            binding.loader.visibility = visibility
        })

        movieListViewModel.isEmptyList.observe(this, Observer {
            if(it){
                binding.layoutEmpty.tvEmptyList.text = getString(R.string.empty_list_movies)
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
        setSupportActionBar(binding.include.toolbar)
        Methods.setupToolbar(this, binding.include.toolbar, genreName, supportActionBar)

        binding.include.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        adapter = MovieListAdapter((movieListViewModel.movieList.value as ArrayList<MovieList>?)!!){
            val intent = MovieDetailActivity.newInstance(this, it.title, it.id)
            startActivity(intent)
        }

        binding.rvMovies.setHasFixedSize(true)
        binding.rvMovies.itemAnimator = DefaultItemAnimator()
        val layoutManager = LinearLayoutManager(this)
        binding.rvMovies.layoutManager = layoutManager
        endlessScrollListener = object : EndlessScrollViewListener(layoutManager, START_PAGE){
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if(page <= movieListViewModel.maxPages.value!!){
                    movieListViewModel.loadMoviesByGenre(genreId, sortType, page)
                }
            }
        }
        binding.rvMovies.addOnScrollListener(endlessScrollListener)
        binding.rvMovies.adapter = adapter

        binding.ivSort.setOnClickListener {
            SortDialogFragment.newInstance().show(supportFragmentManager, SortDialogFragment.TAG)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.left_to_right, R.anim.right_to_left)
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

    override fun sortBy(key: String) {
        sortType = key
        movieListViewModel.loadMoviesByGenre(genreId, sortType, START_PAGE)
    }
}
