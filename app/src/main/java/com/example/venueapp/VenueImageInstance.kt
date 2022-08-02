package com.example.venueapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class VenueImageInstance {

    companion object {
        fun getVenueImageServices(): VenueImageService {
            return Retrofit.Builder()
                .baseUrl("https://api.foursquare.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(VenueImageService::class.java)
        }
    }
}
