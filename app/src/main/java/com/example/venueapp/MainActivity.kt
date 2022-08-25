package com.example.venueapp


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.example.venueapp.data.DataBaseActivity
import com.example.venueapp.data.VenueDatabase
import com.example.venueapp.databinding.ActivityMainBinding
import androidx.room.Room.Companion.databaseBuilder as databaseBuilder1


class MainActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding

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

        binding.DataBaseButton.setOnClickListener{
            val i = Intent(this, DataBaseActivity::class.java)
            startActivity(i)

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