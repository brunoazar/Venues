package com.example.venueapp

import android.widget.ImageView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_SHORT
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VenuesViewModel : ViewModel() {

    val venuesResponseLiveData: MutableLiveData<List<VenueItem>> = MutableLiveData()
    val photoLiveData: MutableLiveData<VenueItem> = MutableLiveData()
    fun getVenues() {
        val venuesService = RetroInstance.getVenuesService()
        val call = venuesService.getVenues("52.372040,4.898948", 15000)

        call.enqueue(object : Callback<VenueResponse> {
            override fun onFailure(call: Call<VenueResponse>, t: Throwable) {
                venuesResponseLiveData.value = listOf()
            }

            override fun onResponse(call: Call<VenueResponse>, response: Response<VenueResponse>) {
                val venueItems = response.body()?.results?.map {
                    VenueItem(
                        it.name, it.geocodes.main.latitude, it.geocodes.main.longitude, it.distance,
                        it.location.address, null, it.fsq_id
                    )
                } ?:listOf()

                venuesResponseLiveData.value = venueItems


                    for(venueItem in venueItems){
                        getImage(venueItem.fsqId)
                    }


            }
        })
    }


    fun getImage(fsq_id: String) {
        val venueImageService = VenueImageInstance.getVenueImageServices()
        val call = venueImageService.getImage(fsq_id)

        call.enqueue(object : Callback<Photo> {
            override fun onFailure(call: Call<Photo>, t: Throwable) {
                print("Error in getting Image")
            }

            override fun onResponse(call: Call<Photo>, response: Response<Photo>) {
                val photoUrl = response.body()?.resultImg?.get(0)?.prefix + "original" + response.body()?.resultImg?.get(0)?.suffix
                val venueItem = venuesResponseLiveData.value?.find { it.fsqId == fsq_id }
                venueItem?.photoURL = photoUrl
                photoLiveData.value =venueItem
            }
        })
    }

}


