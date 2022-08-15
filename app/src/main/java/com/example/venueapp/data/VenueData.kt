package com.example.venueapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

//Represents an entity in our data base which represents the table

@Entity(tableName = "venueDataTable")
data class VenueData (
    @PrimaryKey(autoGenerate = true)
    val distance : Int,
    val name: String,
    val address: String
)