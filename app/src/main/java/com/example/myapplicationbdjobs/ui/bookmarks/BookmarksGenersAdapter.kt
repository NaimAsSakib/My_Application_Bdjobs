package com.example.myapplicationbdjobs.ui.bookmarks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationbdjobs.R

class BookmarksGenersAdapter (private val genersList:List<String>): RecyclerView.Adapter<BookmarksGenersAdapter.MyViewHolder>(){
    class MyViewHolder (itemView:View): RecyclerView.ViewHolder(itemView) {
        val tvGeners: TextView =itemView.findViewById(R.id.tvHorizontalGenersDetailsFragment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_horizontal_rcv_details_fragment, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item= genersList[position]

        if(!item.isEmpty())
        holder.tvGeners.text=item.toString()
    }

    override fun getItemCount(): Int {
       return genersList.size
    }

}