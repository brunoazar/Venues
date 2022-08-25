package com.example.venueapp.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//Dao : Data access Object, it will contain all the methods used to access the data base
@Dao
interface VenueDataDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addVenueData(venueData: VenueData)

    @Query("SELECT * FROM venueDataTable")
    suspend fun readAllData(): List<VenueData>

    @Query("DELETE FROM venueDataTable")
    suspend fun deleteAll()

}