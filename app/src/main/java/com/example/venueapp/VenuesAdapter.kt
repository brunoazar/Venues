package com.example.venueapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.contentValuesOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.venueapp.data.DataBaseActivity
import com.example.venueapp.data.RecyclerViewAdapter
import com.example.venueapp.data.VenueData
import com.google.android.material.internal.ContextUtils.getActivity


class VenuesAdapter(private val venues: MutableList<Result>) : RecyclerView.Adapter<VenuesViewHolder>() {
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








}

