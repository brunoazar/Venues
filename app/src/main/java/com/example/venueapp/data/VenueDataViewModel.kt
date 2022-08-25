import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.venueapp.Result
import com.example.venueapp.data.VenueData
import com.example.venueapp.data.VenueDatabase
import kotlinx.coroutines.launch

//package com.example.venueapp.data
//
//import android.content.Context
//import androidx.lifecycle.LiveData
//
class VenueDataViewModel : ViewModel() {

    var venuesResponseLiveData: MutableLiveData<List<VenueData>> = MutableLiveData()

    fun getData(venueDatabase: VenueDatabase) {
        viewModelScope.launch {
            venuesResponseLiveData.value = venueDatabase.venueDataDao().readAllData()
        }
    }
}