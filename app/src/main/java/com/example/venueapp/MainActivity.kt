package com.example.venueapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.venueapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding
    val venueListFragment = VenueListFragment()
    val googleMapsFragment = GoogleMapsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val venueListFragment = VenueListFragment()
//        val googleMapsFragment = GoogleMapsFragment()

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