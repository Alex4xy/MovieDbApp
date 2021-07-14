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
    private var searchList:ArrayList<MoviesResponse.Result> = arrayListOf()
    private var isSearching:Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: MovieItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.movie_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(!isSearching){
        holder.bind(moviesList[position])
        }else{
            holder.bind(searchList[position])
        }
    }

    override fun getItemCount(): Int {
        return if(!isSearching){
            moviesList.size
        }else{
            searchList.size
        }
    }

    fun updateMoviesList(moviesList:ArrayList<MoviesResponse.Result>, isSearching: Boolean){
        this.isSearching = isSearching
        val oldCount  = this.moviesList.size
        this.moviesList.addAll(moviesList)
        notifyDataSetChanged()
        notifyItemRangeInserted(oldCount, moviesList.size)
    }

    fun updateSearchList(moviesList:ArrayList<MoviesResponse.Result>, isSearching: Boolean){
        this.isSearching = isSearching
        this.moviesList.clear()
        this.searchList.clear()
        this.searchList = moviesList
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