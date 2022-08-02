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
    lateinit var binding: ActivityMainBinding
    lateinit var recyclerAdapter: VenuesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel = ViewModelProvider(this).get(VenuesViewModel::class.java)
        viewModel.venuesResponseLiveData.observe(this, Observer {
            if(it.isNotEmpty()) {
                setupRecyclerView(it)
            } else{
                Toast.makeText(this, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.getVenues()

        if (Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.red)
        }

    }

    private fun setupRecyclerView(results: List<Result>) {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = VenuesAdapter(results)
        binding.recyclerView.adapter = recyclerAdapter
    }

}