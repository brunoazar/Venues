package com.example.venueapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Represents an entity in our data base which represents the table

@Entity(tableName = "venueDataTable")

data class VenueData(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name ="distance") val distance: Int,
    @ColumnInfo(name ="name") val name: String,
    @ColumnInfo(name ="address") val address: String
)