package com.example.venueapp.data

import VenueDataViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.venueapp.databinding.ActivityDataBase2Binding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DataBaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDataBase2Binding
    private lateinit var recyclerAdapter: RecyclerViewAdapter
    private lateinit var venueDataViewModel: VenueDataViewModel
    private lateinit var venueDatabase: VenueDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDataBase2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        venueDataViewModel = ViewModelProvider(this).get(VenueDataViewModel::class.java)
        readDataAndDisplay()
        venueDataViewModel.venuesResponseLiveData.observe(this, Observer {
            setupRecyclerView(it)
        })

    }

    private fun readDataAndDisplay() {
        venueDatabase = VenueDatabase.getDataBase(this)
        venueDataViewModel.getData(venueDatabase)
    }

    private fun setupRecyclerView(results: List<VenueData>) {
        binding?.recyclerViewDatabase?.layoutManager = LinearLayoutManager(this)
        recyclerAdapter = RecyclerViewAdapter(results)
        binding?.recyclerViewDatabase?.adapter = recyclerAdapter
    }

}
