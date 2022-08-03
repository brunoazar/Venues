package com.example.venueapp

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.venueapp.R
import com.example.venueapp.Result

class VenuesViewHolder(private val itemView: View): RecyclerView.ViewHolder(itemView) {

    val context = itemView.context
    val name = itemView.findViewById<TextView>(R.id.name)
    val address = itemView.findViewById<TextView>(R.id.address)
    val distance = itemView.findViewById<TextView>(R.id.distance)
    val image = itemView.findViewById<ImageView>(R.id.image)

    fun setViews(result: VenueItem){
        name.text = result.name
        address.text = result.address
        distance.text = result.distance.toString()
        Glide.with(context).load(result.photoURL).into(image)
    }
}