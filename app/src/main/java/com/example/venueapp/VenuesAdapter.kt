package com.example.venueapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


class VenuesAdapter(private val venues: List<VenueItem>) : RecyclerView.Adapter<VenuesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VenuesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_venues, parent, false)
        return VenuesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VenuesViewHolder, position: Int) {
        holder.setViews(venues[position])
    }

    override fun getItemCount(): Int {
        return venues.size
    }

    fun getImage(venueItem: VenueItem) {
        venues[venues.indexOfFirst { venueItem.fsqId == it.fsqId }].photoURL = venueItem.photoURL
    }


}