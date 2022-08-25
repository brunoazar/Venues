package com.example.venueapp.data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.venueapp.R
import com.example.venueapp.Result
import com.example.venueapp.VenuesViewHolder
import kotlinx.android.synthetic.main.item_venues.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecyclerViewAdapter (private var venueDataList: List<VenueData>) : RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val name = itemView.findViewById<TextView>(R.id.name)
        val address = itemView.findViewById<TextView>(R.id.address)
        val distance = itemView.findViewById<TextView>(R.id.distance)

        fun setViews(venueData: VenueData){
            name.text = venueData.name
            address.text = venueData.address
            distance.text = venueData.distance.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_venues, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.setViews(venueDataList[position])
        }


    override fun getItemCount(): Int {
        return venueDataList.size
    }
}