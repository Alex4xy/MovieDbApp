package com.aleksandar.moviedbapp.ui.movies.landing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.aleksandar.moviedbapp.R
import com.aleksandar.moviedbapp.databinding.MovieItemBinding
import com.aleksandar.moviedbapp.model.MoviesResponse

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private var moviesList:ArrayList<MoviesResponse.Result> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: MovieItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.movie_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    override fun getItemCount(): Int {
        return  moviesList.size
    }

    fun updateMoviesList(moviesList:ArrayList<MoviesResponse.Result>){
        val oldCount  = this.moviesList.size
        this.moviesList.addAll(moviesList)
        notifyItemRangeInserted(oldCount, moviesList.size)
    }

    class ViewHolder(private val binding: MovieItemBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = MovieItemViewModel()
        fun bind(movie: MoviesResponse.Result){
            viewModel.bind(movie)
            binding.viewModel = viewModel
        }
    }
}