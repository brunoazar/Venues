package com.example.venueapp

data class VenueItem (
    val name: String,
    val latitude: Float,
    val longitude: Float,
    val distance: Int,
    val address: String,
    var photoURL: String? = null,
    val fsqId: String
    )