package com.example.myapplicationbdjobs.ui.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationbdjobs.R
import com.example.myapplicationbdjobs.api.models.details.GenresItem
import com.example.myapplicationbdjobs.ui.home.HomeNowShowingRCVAdapter

class DetailsGenersAdapter (private val geners: ArrayList<GenresItem> ) :
RecyclerView.Adapter<DetailsGenersAdapter.MyViewHolder>(){

    class MyViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val tvGeners: TextView=itemView.findViewById(R.id.tvHorizontalGenersDetailsFragment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_horizontal_rcv_details_fragment, parent, false)
        return DetailsGenersAdapter.MyViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.tvGeners.text= geners[position].name
    }

    override fun getItemCount(): Int {
        return geners.size
    }

}