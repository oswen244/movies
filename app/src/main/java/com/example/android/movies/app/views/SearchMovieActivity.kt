package com.example.android.movies.app.views

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.movies.R
import com.example.android.movies.app.model.state.MovieListState
import com.example.android.movies.app.viewmodels.SearchMovieViewModel
import com.example.android.movies.app.views.adapters.MovieListAdapter
import com.example.android.movies.databinding.ActivitySearchMovieBinding
import com.example.android.movies.utils.EndlessScrollViewListener
import com.example.android.movies.utils.Methods
import com.movies.core.domain.entity.MovieEntity
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchMovieBinding
    private val searchMovieViewModel: SearchMovieViewModel by viewModel()
    private lateinit var adapter: MovieListAdapter
    private lateinit var endlessScrollListener: EndlessScrollViewListener
    private val START_PAGE = 1
    private var currentPage = 0
    private var querySearch: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupUI()
    }

    private fun setupObservers() {
        searchMovieViewModel.currentPage.observe(this) { page ->
            currentPage = page
        }

        searchMovieViewModel.movieResult.observe(this) {
            binding.includeEmpty.root.visibility = View.GONE
            binding.includeError.root.visibility = View.GONE
            binding.layoutHint.root.visibility = if (it.isEmpty()) View.VISIBLE else View.GONE
            binding.loader.visibility = View.GONE
            if (currentPage <= START_PAGE) {
                adapter.update(it as ArrayList<MovieEntity>)
            } else {
                adapter.addToList(it)
            }
        }

        searchMovieViewModel.state.observe(this){ state ->
            binding.loader.visibility = View.GONE
            binding.includeEmpty.root.visibility = View.GONE
            binding.includeError.root.visibility = View.GONE
            when(state){
                is MovieListState.LoadingState -> {
                    binding.loader.visibility = View.VISIBLE
                }
                is MovieListState.EmptyListErrorState -> {
                    binding.includeEmpty.root.visibility = View.VISIBLE
                }
                is MovieListState.LoadingErrorState -> {
                    binding.includeError.root.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupUI() {
        setSupportActionBar(binding.toolbarTransparent)
        Methods.setupToolbar(this, binding.toolbarTransparent, getString(R.string.movie_search_title), supportActionBar)
        binding.toolbarTransparent.setNavigationOnClickListener {
            finish()
        }

        adapter = MovieListAdapter((searchMovieViewModel.movieResult.value as ArrayList<MovieEntity>?)!!){
            val intent = MovieDetailActivity.newInstance(this, it.title, it.id)
            startActivity(intent)
        }

        val layoutManager = LinearLayoutManager(this)
        binding.rvSearchResults.setHasFixedSize(true)
        binding.rvSearchResults.itemAnimator = DefaultItemAnimator()
        binding.rvSearchResults.layoutManager = layoutManager

        binding.svSearchMovie.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
               if(newText != null && newText != "" && newText.length >= 2){
                   querySearch = newText
                   searchMovieViewModel.searchMovie(newText, START_PAGE)
               }else{
                   adapter.clearList()
                   binding.layoutHint.root.visibility = View.VISIBLE
               }
                return true
            }

        })

        endlessScrollListener = object : EndlessScrollViewListener(layoutManager, START_PAGE){
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                if(page <= searchMovieViewModel.maxPages.value!! && querySearch != ""){
                    searchMovieViewModel.searchMovie(querySearch, page)
                }
            }
        }

        binding.rvSearchResults.addOnScrollListener(endlessScrollListener)
        binding.rvSearchResults.adapter = adapter
    }

    companion object{
        fun newInstance(context: Context): Intent{
            return Intent(context, SearchMovieActivity::class.java)
        }
    }
}
