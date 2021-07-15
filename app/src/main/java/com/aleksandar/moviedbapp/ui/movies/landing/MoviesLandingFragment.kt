package com.aleksandar.moviedbapp.ui.movies.landing

import android.app.SearchManager
import android.content.Context
import android.os.Bundle
import android.view.*
import android.widget.SearchView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.aleksandar.moviedbapp.R
import com.aleksandar.moviedbapp.base.BaseFragment
import com.aleksandar.moviedbapp.base.ViewModelFactory
import com.aleksandar.moviedbapp.databinding.MoviesLandingFragmentBinding
import com.google.android.material.snackbar.Snackbar


class MoviesLandingFragment : BaseFragment(), SearchView.OnQueryTextListener, SearchView.OnCloseListener {

    private lateinit var viewModel: MoviesLandingViewModel
    private lateinit var binding: MoviesLandingFragmentBinding
    private var errorSnackbar: Snackbar? = null
    private lateinit var searchView: SearchView

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
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_search, menu)
        val searchManager = requireContext().getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search).actionView as SearchView

        searchView.setSearchableInfo(searchManager.getSearchableInfo((context as AppCompatActivity).componentName))
        searchView.isIconifiedByDefault = true
        searchView.setOnQueryTextListener(this)
        searchView.setOnCloseListener(this)
    }

    override fun onClose(): Boolean {
        viewModel.isSearching = false
        return false
    }

    override fun onQueryTextSubmit(p0: String?): Boolean {
        searchView.clearFocus()
        return true
    }

    override fun onQueryTextChange(p0: String?): Boolean {
        p0?.let {
            if(it.isNotEmpty()){
                viewModel.isSearching = true
                viewModel.searchMovies(it)
            }else{
                viewModel.isSearching = false
                viewModel.getMovies()
            }
        }
        return true
    }

    private fun init(){
        viewModel.errorMessage.observe(requireActivity(), { errorMessage ->
            if (errorMessage != null) showError(errorMessage) else hideError()
        })

        viewModel.getMovies()

    }

    private fun setRecyclerViewScrollListener() {
        binding.moviesRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if(!binding.moviesRecycler.canScrollVertically(1) && !viewModel.isSearching){
                    if(viewModel.currentPage <= viewModel.totalAvailablePages){
                        viewModel.currentPage += 1
                        viewModel.getMovies()
                    }
                }
            }
        })
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