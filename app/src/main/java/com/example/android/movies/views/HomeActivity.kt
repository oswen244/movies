package com.example.android.movies.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.movies.R
import com.example.android.movies.databinding.ActivityMainBinding
import com.example.android.movies.viewmodels.GenreViewModel
import com.example.android.movies.views.adapters.GenreAdapter
import org.koin.android.viewmodel.ext.android.viewModel
import java.lang.System.exit

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: GenreAdapter
    private val genreViewModel: GenreViewModel by viewModel<GenreViewModel>()

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
            adapter.updateList(it)
        })

        genreViewModel.isViewLoading.observe(this, Observer {
            val visibility = if(it) View.VISIBLE else View.GONE
            binding.loader.visibility = visibility
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
