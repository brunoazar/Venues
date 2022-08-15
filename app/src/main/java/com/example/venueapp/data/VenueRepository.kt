package com.example.venueapp.data

import androidx.lifecycle.LiveData

class VenueRepository(private val venueDataDao: VenueDataDao) {

    val readAllData: LiveData<List<VenueData>> = venueDataDao.readAllData()

    suspend fun addVenue(venueData: VenueData){
        venueDataDao. addVenueData(venueData)
    }

}