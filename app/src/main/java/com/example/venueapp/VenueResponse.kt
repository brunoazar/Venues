package com.example.venueapp

data class VenueResponse(
      val results: List<Result>
)

data class Result(
    val name: String,
    val distance: Int,
    val location : Location,
    val geocodes: Geocode,
    val fsq_id: String
)

data class Geocode(
    val main: Main
)

data class Main(
    val latitude: Float,
    val longitude: Float
)

data class Location(
    val address :String
)






