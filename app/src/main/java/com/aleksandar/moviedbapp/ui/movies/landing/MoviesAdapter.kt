package com.aleksandar.moviedbapp.ui.movies.landing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.aleksandar.moviedbapp.R
import com.aleksandar.moviedbapp.databinding.MovieItemBinding
import com.aleksandar.moviedbapp.model.MoviesResponse
import com.aleksandar.moviedbapp.util.ID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {
    private var moviesList:ArrayList<MoviesResponse.Result> = arrayListOf()
    private var searchList:ArrayList<MoviesResponse.Result> = arrayListOf()
    private var isSearching:Boolean = false
    private lateinit var binding: MovieItemBinding
    private var parentWidth = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.movie_item, parent, false)

        if (parentWidth == 0) parentWidth = parent.measuredWidth
        binding.root.layoutParams = ViewGroup.LayoutParams(
            (parentWidth*0.5).toInt(),
            ViewGroup.LayoutParams.WRAP_CONTENT)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //must set to false to maintain recycler position
        holder.setIsRecyclable(false)

        if(!isSearching){
            holder.bind(moviesList[position])
        }else{
            holder.bind(searchList[position])
        }

        binding.cardMovieItem.setOnClickListener {
            CoroutineScope(Dispatchers.Main).launch {
                val id: String = if(!isSearching){
                    moviesList[position].id.toString()
                }else{
                    searchList[position].id.toString()
                }
                val bundle = Bundle()
                bundle.putString(ID, id)
                it.findNavController().navigate(R.id.action_mainFragment_to_detailsFragment, bundle)
            }
        }
    }

    override fun getItemCount(): Int {
        return if(!isSearching){
            moviesList.size
        }else{
            searchList.size
        }
    }

    fun clear(){
        this.moviesList.clear()
        this.searchList.clear()
        this.isSearching = false
        notifyDataSetChanged()
    }

    fun updateMoviesList(moviesList: ArrayList<MoviesResponse.Result>, isSearching: Boolean){
        this.isSearching = isSearching
        val oldCount  = moviesList.size
        this.moviesList.addAll(moviesList)
        notifyItemRangeInserted(oldCount, moviesList.size)
        notifyDataSetChanged()
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