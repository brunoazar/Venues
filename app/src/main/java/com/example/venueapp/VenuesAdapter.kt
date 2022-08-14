package com.example.venueapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


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

    fun addItemToDataBase(i: Int, venue: Result){
        venues.add(i,venue)
        notifyDataSetChanged()
    }



}
