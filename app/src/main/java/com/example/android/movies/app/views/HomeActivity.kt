package com.example.android.movies.app.views

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.movies.R
import com.example.android.movies.app.model.state.GenresState
import com.example.android.movies.databinding.ActivityMainBinding
import com.example.android.movies.app.viewmodels.GenreViewModel
import com.example.android.movies.app.views.adapters.GenreAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GenreAdapter
    private val genreViewModel: GenreViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupObservers()
        setupUI()

    }

    private fun setupObservers() {
        genreViewModel.genreList.observe(this, Observer {
            binding.layoutEmpty.apply { View.GONE }
            binding.layoutError.apply { View.GONE }
            binding.loader.visibility = View.GONE
            adapter.updateList(it)
        })

        genreViewModel.isEmptyList.observe(this, Observer {
            if(it){
                binding.layoutEmpty.root.visibility = View.VISIBLE
                binding.layoutError.root.visibility = View.GONE
            }else{
                binding.layoutEmpty.root.visibility = View.GONE
            }
        })

        genreViewModel.onMessageError.observe(this, Observer {
            binding.layoutError.root.visibility = View.VISIBLE
            binding.layoutEmpty.root.visibility = View.GONE
        })

        genreViewModel.state.observe(this){ state ->
            binding.loader.visibility = View.GONE
            binding.layoutError.root.visibility = View.GONE
            when(state) {
                is GenresState.LoadingErrorState -> {
                    binding.layoutError.root.visibility = View.VISIBLE
                }
                is GenresState.LoadingState -> {
                    binding.loader.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupUI() {
        adapter = GenreAdapter(genreViewModel.genreList.value!!){
            openListByGenre(it.id.toString(), it.name)
        }
        binding.rvMoviesGenres.layoutManager = LinearLayoutManager(this)
        binding.rvMoviesGenres.adapter = adapter

        binding.btnSearchMovie.setOnClickListener {
            openMovieSearch()
        }

        binding.rvMoviesGenres.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                binding.btnSearchMovie.extend()
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(dy != 0){
                    binding.btnSearchMovie.shrink()
                }
            }
        })
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

    override fun onResume() {
        super.onResume()
        genreViewModel.loadMovieGenres()
    }
}
