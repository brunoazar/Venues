package com.example.venueapp

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroInstance {


    companion object {

        val baseURL = "https://api.foursquare.com"
        fun getVenuesService(): VenuesService {
            return Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(VenuesService::class.java)
        }
    }
}