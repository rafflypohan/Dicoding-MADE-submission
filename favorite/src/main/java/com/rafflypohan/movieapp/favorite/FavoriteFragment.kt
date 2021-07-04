package com.rafflypohan.movieapp.favorite

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rafflypohan.movieapp.core.ui.MovieAdapter
import com.rafflypohan.movieapp.detail.DetailActivity
import com.rafflypohan.movieapp.favorite.databinding.FragmentFavoriteBinding
import com.rafflypohan.movieapp.favorite.di.favoriteModule
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules


class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding

    private val movieAdapter: MovieAdapter by lazy { MovieAdapter() }
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null){
            loadKoinModules(favoriteModule)

            movieAdapter.onItemClick = { selectedData ->
                val intent = Intent(activity, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
                startActivity(intent)
            }

            loadKoinModules(favoriteModule)
            favoriteViewModel.favoriteMovie.observe(viewLifecycleOwner, { favorite ->
                showLoading(false)
                if (favorite.isNotEmpty()){
                    showPlaceholder(false)
                    movieAdapter.setMovie(favorite)
                } else {
                    binding?.rvFavorite?.visibility = View.GONE
                    showPlaceholder(true)
                }
            })

            recyclerViewConfig()
        }
    }

    private fun recyclerViewConfig() {
        with(binding?.rvFavorite){
            this?.let {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
    }

    private fun showPlaceholder(state: Boolean) {
        with(binding?.tvFavoritePlaceholder) {
            this?.visibility = if (state) View.VISIBLE
            else View.GONE
        }
        with(binding?.animPlaceholder){
            this?.visibility = if (state) View.VISIBLE
            else View.GONE
        }
    }

    private fun showLoading(state: Boolean) {
        with(binding?.progressBar) {
            this?.visibility = if (state) View.VISIBLE
            else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        unloadKoinModules(favoriteModule)
        _binding = null
    }
}