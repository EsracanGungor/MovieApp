package com.esracangungor.movieapp.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.esracangungor.movieapp.R
import com.esracangungor.movieapp.service.TMDB_POSTER_BASE_URL
import com.esracangungor.movieapp.databinding.ItemPopularMoviesBinding
import com.esracangungor.movieapp.model.PopularMovieItem

class PopularMovieListAdapter(
    private val movieItemClickInterface: MovieItemClickInterface
) : RecyclerView.Adapter<PopularMovieListAdapter.ViewHolder>() {

    private val movieList = ArrayList<PopularMovieItem>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemPopularMoviesBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    inner class ViewHolder(private val binding: ItemPopularMoviesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: PopularMovieItem) {
            binding.apply {
                tvMovieItemTitle.text = movie.title
                tvMovieItemReleaseDate.text = movie.releaseDate

                val moviePosterURL = TMDB_POSTER_BASE_URL + movie.posterPath
                ivMovieItem.load(moviePosterURL) {
                    placeholder(R.drawable.image)
                    crossfade(true)
                    crossfade(600)
                    transformations(RoundedCornersTransformation(20f))
                }
                ivMovieItem.setOnClickListener {
                    movieItemClickInterface.onMovieItemClick(movie)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(movieList[position])

    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateMovieList(newMovieList: List<PopularMovieItem>) {
        movieList.clear()
        movieList.addAll(newMovieList)
        notifyDataSetChanged()
    }

    interface MovieItemClickInterface {
        fun onMovieItemClick(movieItem: PopularMovieItem)
    }
}

