package com.example.venueapp

import android.os.Bundle
import android.os.PersistableBundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.venueapp.databinding.FragmentGoogleMapsBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import dagger.hilt.android.AndroidEntryPoint


class GoogleMapsFragment : Fragment(), OnMapReadyCallback {

    private val viewModel: VenuesViewModel by activityViewModels()
    var venueList : List<Result>? = null


    private var binding: FragmentGoogleMapsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGoogleMapsBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.MapView?.getMapAsync(this)
        binding?.MapView?.onCreate(savedInstanceState)
        viewModel.venuesResponseLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                venueList=it
            } else {
                //Toast.makeText(this, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
    }



    override fun onDestroyView() {
        super.onDestroyView()
        binding?.root
    }

    override fun onMapReady(p0: GoogleMap) {
            for (venue: Result in venueList!!){
                val latitudeLongitude = LatLng(
                    venue.geocodes.main.latitude.toDouble(),
                    venue.geocodes.main.longitude.toDouble()
                )
                p0.addMarker(
                    MarkerOptions().position(latitudeLongitude)
                        .title("" + latitudeLongitude.latitude + ":" + latitudeLongitude.longitude)
               )
                //p0.animateCamera(CameraUpdateFactory.zoomTo(14.9f))
                p0.moveCamera(CameraUpdateFactory.newLatLngZoom(latitudeLongitude,14.9f))

            }

    }

    override fun onStart() {
        super.onStart()
        binding?.MapView?.onStart()
    }

    override fun onResume() {
        super.onResume()
        binding?.MapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding?.MapView?.onPause()
    }

    override fun onStop() {
        super.onStop()
        binding?.MapView?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding?.MapView?.onDestroy()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        binding?.MapView?.onSaveInstanceState(outState)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        binding?.MapView?.onLowMemory()
    }


}




