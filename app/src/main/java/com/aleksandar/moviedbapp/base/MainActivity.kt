package com.aleksandar.moviedbapp.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.aleksandar.moviedbapp.R
import com.aleksandar.moviedbapp.ui.movies.details.DetailsViewModel
import com.aleksandar.moviedbapp.ui.movies.landing.MoviesLandingViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var landingViewModel: MoviesLandingViewModel
    private lateinit var detailsViewModel: DetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        navController = findNavController(R.id.nav_host_fragment)

        landingViewModel = ViewModelProvider(this, ViewModelFactory(this)).get(MoviesLandingViewModel::class.java)
        detailsViewModel = ViewModelProvider(this, ViewModelFactory(this)).get(DetailsViewModel::class.java)
    }

    override fun onBackPressed() {
        if (navController.currentDestination?.id == R.id.detailsFragment) {
            landingViewModel.moviesAdapter.clear()
            landingViewModel.currentPage = 1
            landingViewModel.totalAvailablePages = 1
            landingViewModel.isSearching = false
            super.onBackPressed()
        }else {
            super.onBackPressed()
        }
    }
}