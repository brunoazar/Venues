package com.example.venueapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.venueapp.databinding.FragmentVenueListBinding
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class GoogleMapsFragment : Fragment() {



    private var binding: FragmentVenueListBinding? = null
    var venueFragment= VenueListFragment()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_google_maps, container, false)

        val supportMapFragment = SupportMapFragment()
        childFragmentManager.findFragmentById(R.id.fragmentMap)


        supportMapFragment.getMapAsync(OnMapReadyCallback{

            fun onMapReady(googleMap : GoogleMap){
                val venueList =venueFragment.venueList
                googleMap.setOnMapClickListener(GoogleMap.OnMapClickListener {

                    fun onMapClick(latlng: LatLng){
                        val markerOptions= MarkerOptions()
                        markerOptions.position(latlng)
                        markerOptions.title("" + latlng.latitude + ":" + latlng.longitude)

                        googleMap.clear()
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(
                            latlng, 10.0F
                        ))

                        googleMap.addMarker(markerOptions)
                    }
                })

                 for(venue: Result in venueList){
                     val latitudeLongitude = LatLng(
                         venue.geocodes[0].main.latitude.toDouble(),
                         venue.geocodes[0].main.longitude.toDouble())
                     googleMap.addMarker(MarkerOptions().position(latitudeLongitude).title("" + latitudeLongitude.latitude + ":" + latitudeLongitude.longitude))
                     googleMap.animateCamera(CameraUpdateFactory.zoomTo(15.0f))
                     googleMap.moveCamera(CameraUpdateFactory.newLatLng(latitudeLongitude))

                 }


            }
        })
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding?.root
    }



}