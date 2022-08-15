package com.example.venueapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.venueapp.data.VenueData
import com.example.venueapp.data.VenueDataViewModel


class VenuesAdapter(private val venues: MutableList<Result>) : RecyclerView.Adapter<VenuesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenuesViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_venues, parent, false)
        return VenuesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VenuesViewHolder, position: Int) {
        holder.setViews(venues[position])
    }

    override fun getItemCount(): Int {
        return venues.size
    }

    fun deleteItem(i: Int) {
        venues.removeAt(i)
        notifyDataSetChanged()
    }

    private lateinit var venuedataViewModel: VenueDataViewModel

    fun addItemToDataBase(result : Result){
        val name = result.name
        val distance = result.distance
        val address = result.location.address
        
        val venueData = VenueData(distance,name,address)


        notifyDataSetChanged()
    }



}
