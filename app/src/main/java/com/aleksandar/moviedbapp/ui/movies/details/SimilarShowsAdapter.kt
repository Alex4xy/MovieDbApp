package com.aleksandar.moviedbapp.ui.movies.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.aleksandar.moviedbapp.R
import com.aleksandar.moviedbapp.databinding.SimilarShowItemBinding
import com.aleksandar.moviedbapp.model.MoviesResponse
import com.aleksandar.moviedbapp.util.ID

class SimilarShowsAdapter : RecyclerView.Adapter<SimilarShowsAdapter.ViewHolder>() {
    private var moviesList:ArrayList<MoviesResponse.Result> = arrayListOf()
    private lateinit var binding: SimilarShowItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.similar_show_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //must set to false to maintain recycler position
        holder.setIsRecyclable(false)
        holder.bind(moviesList[position])

        binding.imageViewMoviePoster.setOnClickListener {
                val id = moviesList[position].id.toString()
                val bundle = Bundle()
                bundle.putString(ID, id)
                it.findNavController().navigate(R.id.action_detailsFragment_self, bundle)
            }

        }

    override fun getItemCount(): Int {
        return moviesList.size
    }


    fun updateSimilarList(moviesList: ArrayList<MoviesResponse.Result>){
        this.moviesList = moviesList
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: SimilarShowItemBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = SimilarShowItemViewModel()
        fun bind(movie: MoviesResponse.Result){
            viewModel.bind(movie)
            binding.viewModel = viewModel
        }
    }
}