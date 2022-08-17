package com.example.venueapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.venueapp.databinding.FragmentVenueListBinding
import dagger.hilt.android.AndroidEntryPoint


class VenueListFragment : Fragment() {


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
        //val viewModel = ViewModelProvider(this).get(VenuesViewModel::class.java)
        viewModel.venuesResponseLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                setupRecyclerView(it)
                venueList=it
            } else {
                //Toast.makeText(this, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.getVenues()
    }

    private fun setupRecyclerView(results: List<Result>) {
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerAdapter = VenuesAdapter(results)
        binding?.recyclerView?.adapter = recyclerAdapter
    }


}