package com.example.venueapp


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VenuesViewModel: ViewModel() {

    val venuesResponseLiveData: MutableLiveData<List<Result>> = MutableLiveData()

    fun getVenues() {
        val venuesService = RetroInstance.getVenuesService()
        val call  = venuesService.getVenues("52.372040,4.898948", 15000)

        call.enqueue(object: Callback<VenueResponse>{
            override fun onFailure(call: Call<VenueResponse>, t: Throwable) {
                venuesResponseLiveData.value = listOf()
            }

            override fun onResponse(call: Call<VenueResponse>, response: Response<VenueResponse>) {
                venuesResponseLiveData.value = response.body()?.results ?: listOf()

            }
        })
    }

    fun getImages(fsq_id:String, results:  List<Result> ) {
            val venueImageService = VenueImageInstance.getVenueImageServices()
            val call = venueImageService.getImage(fsq_id)

            call.enqueue(object: Callback<Photo>{
                override fun onFailure(call: Call<Photo>, t: Throwable) {
                    print("Error in getting Images")
                            }
                override fun onResponse(call: Call<Photo>, response: Response<Photo>) {

                }
            })
    }

}


