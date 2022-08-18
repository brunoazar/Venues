package com.example.venueapp

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.venueapp.databinding.FragmentVenueListBinding



class VenueListFragment : Fragment(), RecyclerViewInterface{

    val googleMapsFragment = GoogleMapsFragment()
     var binding: FragmentVenueListBinding? = null
     lateinit var recyclerAdapter: VenuesAdapter
     lateinit var venueList : List<Result>

     private val viewModel : VenuesViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentVenueListBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.venuesResponseLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                setupRecyclerView(it)
                venueList=it
            }
        })
        viewModel.getVenues()
    }

    private fun setupRecyclerView(results: List<Result>) {
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerAdapter = VenuesAdapter(results, this)
        binding?.recyclerView?.adapter = recyclerAdapter
    }

    override fun onClickVenue(venue : Result) {
        viewModel.clickedVenue = venue
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(R.id.FrameLayout, googleMapsFragment)
            commit()
        }

    }



}