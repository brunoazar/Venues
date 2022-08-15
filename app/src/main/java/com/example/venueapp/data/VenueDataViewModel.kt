package com.example.venueapp.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VenueDataViewModel(application: Application) : AndroidViewModel(application) {

    private val readAllData: LiveData<List<VenueData>>
    private val repository: VenueRepository

    init {
        val venueDataDao = VenueDataBase.getDataBase(application).venueDataDao()
        repository = VenueRepository(venueDataDao)
        readAllData = repository.readAllData
    }

    fun addVenueData(venueData: VenueData) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addVenue(venueData)
        }
    }

}