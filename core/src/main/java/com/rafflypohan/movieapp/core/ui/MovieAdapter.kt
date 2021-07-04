package com.rafflypohan.movieapp.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rafflypohan.movieapp.core.data.source.remote.network.ApiKey
import com.rafflypohan.movieapp.core.databinding.ItemListMovieBinding
import com.rafflypohan.movieapp.core.domain.model.Movie

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private var listMovie = ArrayList<Movie>()
    var onItemClick: ((Movie) -> Unit)? = null

    fun setMovie(newListMovie: List<Movie>?){
        if (newListMovie == null) return
        listMovie.clear()
        listMovie.addAll(newListMovie)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemListMovieBinding = ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemListMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listMovie[position])
    }

    override fun getItemCount(): Int = listMovie.size

    inner class MovieViewHolder(private val binding: ItemListMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener{
                onItemClick?.invoke(listMovie[adapterPosition])
            }
        }
        fun bind(movie: Movie){
            with(binding){
                Glide.with(itemView.context)
                    .load(ApiKey.IMAGE_URL + movie.posterPath)
                    .into(ivMovie)
                tvTitle.text = movie.title
                tvRating.text = movie.voteAverage.toString()
                tvOverview.text = movie.overview
            }
        }
    }

}