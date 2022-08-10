package com.example.venueapp

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.venueapp.databinding.ActivityMainBinding 

class MainActivity : AppCompatActivity(){


    lateinit var binding : ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val venueListFragment = VenueListFragment()
        val googleMapsFragment = GoogleMapsFragment()

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.FrameLayout, venueListFragment)
            commit()
        }

        binding.VenueListButton.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.FrameLayout, venueListFragment)
                addToBackStack(null)
                commit()
            }
        }

        binding.GoogleMapsButton.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.FrameLayout, googleMapsFragment)
                addToBackStack(null)
                commit()
            }
        }



    }

}