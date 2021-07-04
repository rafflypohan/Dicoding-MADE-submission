package com.rafflypohan.movieapp.home.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafflypohan.movieapp.core.data.Resource
import com.rafflypohan.movieapp.core.ui.MovieAdapter
import com.rafflypohan.movieapp.detail.DetailActivity
import com.rafflypohan.movieapp.home.databinding.FragmentHomeBinding
import com.rafflypohan.movieapp.home.di.homeModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class HomeFragment :  Fragment(){
    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val movieAdapter: MovieAdapter by lazy { MovieAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){

            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            loadKoinModules(homeModule)

            homeViewModel.popularMovie.observe(viewLifecycleOwner, {movie ->
                if (movie != null){
                    when (movie){
                        is Resource.Loading -> {binding.progressBar.visibility = View.VISIBLE}
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            movieAdapter.setMovie(movie.data)
                        }
                        is Resource.Error -> {}
                    }
                }
            })
            recyclerViewConfig()
        }

    }

    private fun recyclerViewConfig() {
        with(binding.rvMovie){
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

}