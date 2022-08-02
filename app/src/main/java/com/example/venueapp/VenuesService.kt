package com.example.venueapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers


import retrofit2.http.Query

interface VenuesService {

    @Headers(
        "Authorization:fsq3hyZbbM+ITLoW8QMyWi7A6iXhN4PtYeITRLl5tu6cICg="
    )
    @GET("/v3/places/search")
    fun getVenues(@Query("ll") ll : String,
                  @Query("radius") radius : Int): Call<VenueResponse>
}