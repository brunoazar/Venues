package com.example.venueapp

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VenuesViewHolder(itemView: View, private val onClickListener: RecyclerViewInterface): RecyclerView.ViewHolder(itemView)  {
    private val name = itemView.findViewById<TextView>(R.id.name)
    private val address = itemView.findViewById<TextView>(R.id.address)
    private val distance = itemView.findViewById<TextView>(R.id.distance)

    fun setViews(result: Result) {
        name.text = result.name
        address.text = result.location.address
        distance.text = result.distance.toString()

        itemView.setOnClickListener {
            onClickListener.onClickVenue(result)
        }
    }

}



