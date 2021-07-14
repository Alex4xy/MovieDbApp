package com.aleksandar.moviedbapp.ui.movies.landing

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
import com.aleksandar.moviedbapp.databinding.MoviesLandingFragmentBinding

class MoviesLandingFragment : BaseFragment() {

    private lateinit var viewModel: MoviesLandingViewModel
    private lateinit var binding: MoviesLandingFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.movies_landing_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), ViewModelFactory(activity as AppCompatActivity)).get(
            MoviesLandingViewModel::class.java)
        binding.viewModel = viewModel

        init()

    }

    private fun init(){
        viewModel.getMovies()
    }

}