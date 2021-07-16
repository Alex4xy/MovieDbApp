package com.aleksandar.moviedbapp.ui.movies.details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.aleksandar.moviedbapp.R
import com.aleksandar.moviedbapp.base.BaseFragment
import com.aleksandar.moviedbapp.base.ViewModelFactory
import com.aleksandar.moviedbapp.databinding.DetailsFragmentBinding
import com.aleksandar.moviedbapp.util.ID

class DetailsFragment : BaseFragment() {

    private lateinit var viewModel: DetailsViewModel
    private lateinit var binding: DetailsFragmentBinding
    var id : String = ""

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.details_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), ViewModelFactory(activity as AppCompatActivity)).get(DetailsViewModel::class.java)
        binding.viewModel = viewModel

        init()
        initListeners()

    }

    private fun init(){
        id = arguments?.getString(ID)?:""
        if(id.isNotEmpty()){
            viewModel.getMovieDetails(id)
            viewModel.getSimilar(id)

            val isFavourite = viewModel.getFavourite(id)
            binding.imageViewFavourite.setImageResource(if (isFavourite) R.drawable.icon_favurites_with_circle_background_selected else R.drawable.icon_favurites_with_circle_background)
            binding.imageViewFavourite.isSelected = !isFavourite
        }
    }

    private fun initListeners(){
        binding.imageViewFavourite.setOnClickListener {
            val isSelected = binding.imageViewFavourite.isSelected
            binding.imageViewFavourite.isSelected = !isSelected
            binding.imageViewFavourite.setImageResource(if (isSelected) R.drawable.icon_favurites_with_circle_background_selected else R.drawable.icon_favurites_with_circle_background)

            if(id.isNotEmpty()){
                viewModel.updateFavourite(id, isSelected)
            }
        }
    }
}