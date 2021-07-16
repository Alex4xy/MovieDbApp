package com.aleksandar.moviedbapp.ui.movies.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.aleksandar.moviedbapp.R
import com.aleksandar.moviedbapp.databinding.SeasonItemBinding
import com.aleksandar.moviedbapp.model.MovieDetailsResponse

class SeasonsAdapter : RecyclerView.Adapter<SeasonsAdapter.ViewHolder>() {
    private var seasonsList:List<MovieDetailsResponse.Season> = arrayListOf()
    private lateinit var binding: SeasonItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.season_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(seasonsList[position])
        }

    override fun getItemCount(): Int {
        return seasonsList.size
    }


    fun updateSeasonsList(seasonsList: List<MovieDetailsResponse.Season>){
        this.seasonsList = seasonsList
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: SeasonItemBinding):RecyclerView.ViewHolder(binding.root){
        private val viewModel = SeasonItemViewModel()
        fun bind(movie: MovieDetailsResponse.Season){
            viewModel.bind(movie)
            binding.viewModel = viewModel
        }
    }
}