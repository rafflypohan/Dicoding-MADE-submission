package com.rafflypohan.movieapp.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.rafflypohan.movieapp.R
import com.rafflypohan.movieapp.core.data.source.remote.network.ApiKey.IMAGE_URL
import com.rafflypohan.movieapp.core.domain.model.Movie
import com.rafflypohan.movieapp.databinding.ActivityDetailBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private var _binding: ActivityDetailBinding? = null
    private val binding get() = _binding

    private val detailViewModel: DetailViewModel by viewModel()

    private var movie: Movie? = null
    private var menu: Menu? = null

    private var stateFavorite: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater, container, false)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbar)
        supportActionBar?.apply {
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        movie = intent.getParcelableExtra(EXTRA_DATA)
        showDetailMovie(movie)
    }

    private fun showDetailMovie(movie: Movie?) {
        binding?.ivMovie?.let {
            Glide.with(this)
                .load(IMAGE_URL + movie?.posterPath)
                .into(it)
        }

        movie?.let { detailMovie ->
            with(binding) {
                this?.let {
                    toolbarTitle.text = detailMovie.title
                    tvTitle.text = detailMovie.title
                    tvReleaseDate.text = resources.getString(R.string.text_release_date, detailMovie.releaseDate)
                    tvRating.text = resources.getString(R.string.text_rating, detailMovie.voteAverage.toString())
                    tvOverviewDescription.text = detailMovie.overview
                }

            }
        }

        showLoading(false)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        this.menu = menu

        stateFavorite = movie?.isFavorite
        stateFavorite?.let { setFavoriteState(it) }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            setFavorite()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    private fun setFavorite() {
        stateFavorite = !stateFavorite!!
        Log.d("$this", "$stateFavorite")
        movie?.let { detailViewModel.setFavoriteMovie(it, stateFavorite!!) }
        setFavoriteState(stateFavorite!!)
        if (stateFavorite!!){
            Toast.makeText(this, "Successfully added to Favorite", Toast.LENGTH_SHORT)
                .show()
        } else {
            Toast.makeText(this, "Successfully removed from Favorite", Toast.LENGTH_SHORT)
                .show()
        }

    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state){
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_navigation_favorite)
            menuItem?.icon?.setTint(getColor(R.color.red))
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_navigation_favorite_outlined)
        }
    }

    private fun showLoading(state: Boolean) {
        with(binding?.progressBar) {
            this?.visibility = if (state) View.VISIBLE
            else View.GONE
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}