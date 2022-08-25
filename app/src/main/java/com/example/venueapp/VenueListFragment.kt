package com.example.venueapp

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.venueapp.data.VenueData
import com.example.venueapp.data.VenueDatabase
import com.example.venueapp.databinding.FragmentVenueListBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class VenueListFragment : Fragment() {

    private var binding: FragmentVenueListBinding? = null
    lateinit var recyclerAdapter: VenuesAdapter
    lateinit var venueList: List<Result>
    private lateinit var venuesViewModel: VenuesViewModel
    private lateinit var venueDatabase: VenueDatabase
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
        venuesViewModel = ViewModelProvider(this).get(VenuesViewModel::class.java)
        venuesViewModel.venuesResponseLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                setupRecyclerView(it)
                venueList = it
            } else {

            }
        })

        venuesViewModel.getVenues()

        val swipeGesture = object : SwipeGesture(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                when (direction) {
                    ItemTouchHelper.RIGHT -> {
                        val savedItem = venueList[viewHolder.adapterPosition]
                        addItemToDataBase(savedItem)
                    }
                }
            }
        }

        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(binding?.recyclerView)

    }


    private fun setupRecyclerView(results: List<Result>) {
        binding?.recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerAdapter = VenuesAdapter(results as MutableList<Result>)
        binding?.recyclerView?.adapter = recyclerAdapter
    }


    fun addItemToDataBase(result: Result) {
        val name = result.name
        val distance = result.distance
        val address = result.location.address
        val venueData = VenueData(null, distance, name, address)

        GlobalScope.launch(Dispatchers.IO) {
            venueDatabase.venueDataDao().addVenueData(venueData)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        venueDatabase = VenueDatabase.getDataBase(context)
    }

}