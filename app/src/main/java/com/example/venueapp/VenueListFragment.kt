package com.example.venueapp

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
import com.example.venueapp.databinding.FragmentVenueListBinding


class VenueListFragment : Fragment() {


     var binding: FragmentVenueListBinding? = null
     lateinit var recyclerAdapter: VenuesAdapter
     lateinit var venueList: List<Result>
     private val context = MainActivity()



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
        val viewModel = ViewModelProvider(this).get(VenuesViewModel::class.java)
        viewModel.venuesResponseLiveData.observe(viewLifecycleOwner, Observer {
            if (it.isNotEmpty()) {
                setupRecyclerView(it)
                venueList=it
            } else {
                //Toast.makeText(this, "Error in getting list", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.getVenues()

        val swipeGesture = object: SwipeGesture(this){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

                when(direction){
                    ItemTouchHelper.RIGHT ->{
                        val savedItem = venueList[viewHolder.adapterPosition]
                        recyclerAdapter.deleteItem(viewHolder.adapterPosition)
                        recyclerAdapter.addItemToDataBase(venueList.size, savedItem)
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


}