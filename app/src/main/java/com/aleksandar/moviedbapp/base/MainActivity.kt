package com.aleksandar.moviedbapp.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aleksandar.moviedbapp.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}