package com.example.android.movies.views

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.movies.R
import com.example.android.movies.databinding.ActivitySearchMovieBinding
import com.example.android.movies.models.MovieList
import com.example.android.movies.utils.EndlessScrollViewListener
import com.example.android.movies.utils.Methods
import com.example.android.movies.viewmodels.SearchMovieViewModel
import com.example.android.movies.views.adapters.MovieListAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class SearchMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchMovieBinding
    private val searchMovieViewModel: SearchMovieViewModel by viewModel<SearchMovieViewModel>()
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
        searchMovieViewModel.currentPage.observe(this, Observer {page ->
            currentPage = page
        })

        searchMovieViewModel.movieResult.observe(this, Observer {
            binding.includeEmpty.root.visibility = View.GONE
            binding.includeError.root.visibility = View.GONE
            binding.layoutHint.root.visibility = if(it.isEmpty()) View.VISIBLE else View.GONE
            if(currentPage <= START_PAGE){
                adapter.update(it as ArrayList<MovieList>)
            }else{
                adapter.addToList(it)
            }
        })

        searchMovieViewModel.isViewLoading.observe(this, Observer {
            val visibility = if(it) View.VISIBLE else View.GONE
            binding.loader.visibility = visibility
        })

        searchMovieViewModel.isEmptyList.observe(this, Observer {
            if(it){
                binding.includeEmpty.tvEmptyList.text = getString(R.string.empty_list_movies)
                binding.includeEmpty.root.visibility = View.VISIBLE
                binding.includeError.root.visibility = View.GONE
            }else{
                binding.includeEmpty.root.visibility = View.GONE
            }
        })

        searchMovieViewModel.onMessageError.observe(this, Observer {
            binding.includeError.root.visibility = View.VISIBLE
            binding.includeEmpty.root.visibility = View.GONE
        })
    }

    private fun setupUI() {
        setSupportActionBar(binding.toolbarTransparent)
        Methods.setupToolbar(this, binding.toolbarTransparent, "Movie search", supportActionBar)
        binding.toolbarTransparent.setNavigationOnClickListener {
            finish()
        }

        adapter = MovieListAdapter((searchMovieViewModel.movieResult.value as ArrayList<MovieList>?)!!){
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
