package com.aleksandar.moviedbapp.ui.movies.landing

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.aleksandar.moviedbapp.R
import com.aleksandar.moviedbapp.base.BaseFragment
import com.aleksandar.moviedbapp.base.ViewModelFactory
import com.aleksandar.moviedbapp.databinding.MoviesLandingFragmentBinding
import com.google.android.material.snackbar.Snackbar

class MoviesLandingFragment : BaseFragment() {

    private lateinit var viewModel: MoviesLandingViewModel
    private lateinit var binding: MoviesLandingFragmentBinding
    private var errorSnackbar: Snackbar? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.movies_landing_fragment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity(), ViewModelFactory(activity as AppCompatActivity)).get(MoviesLandingViewModel::class.java)
        binding.viewModel = viewModel

        init()
        setRecyclerViewScrollListener()
    }

    private fun setRecyclerViewScrollListener() {
        binding.moviesRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(!binding.moviesRecycler.canScrollVertically(1)){
                if(viewModel.currentPage <= viewModel.totalAvailablePages){
                    viewModel.currentPage += 1
                    viewModel.isLoadingMore.value = View.VISIBLE
                    viewModel.getMovies()
                }
                }
            }
        })
    }

    private fun init(){
        viewModel.errorMessage.observe(requireActivity(), { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

        viewModel.getMovies()

    }


    private fun showError(@StringRes errorMessage:Int){
        errorSnackbar = Snackbar.make(binding.root, errorMessage, Snackbar.LENGTH_INDEFINITE)
        errorSnackbar?.setAction(R.string.retry, viewModel.errorClickListener)
        errorSnackbar?.show()
    }

    private fun hideError(){
        errorSnackbar?.dismiss()
    }

}