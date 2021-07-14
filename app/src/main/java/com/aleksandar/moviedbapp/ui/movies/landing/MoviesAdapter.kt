package com.aleksandar.moviedbapp.ui.movies.landing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.aleksandar.moviedbapp.R
import com.aleksandar.moviedbapp.databinding.MovieItemBinding
import com.aleksandar.moviedbapp.model.MoviesResponse

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private lateinit var moviesList:List<MoviesResponse.Result>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: MovieItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.movie_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(moviesList[position])
    }

    override fun getItemCount(): Int {
        return if(::moviesList.isInitialized) moviesList.size else 0
    }

    fun updateMoviesList(postList:List<MoviesResponse.Result>){
        this.moviesList = postList
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: MovieItemBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = MovieItemViewModel()
        fun bind(movie: MoviesResponse.Result){
            viewModel.bind(movie)
            binding.viewModel = viewModel
        }
    }
}