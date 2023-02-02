package com.example.myapplicationbdjobs.ui.bookmarks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationbdjobs.R
import kotlinx.coroutines.NonDisposableHandle.parent

class BookmarksGenersAdapter (private val genersList:ArrayList<String>): RecyclerView.Adapter<BookmarksGenersAdapter.MyViewHolder>(){
    class MyViewHolder (itemView:View): RecyclerView.ViewHolder(itemView) {
        val tvGeners: TextView =itemView.findViewById(R.id.tvHorizontalGenersDetailsFragment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_vertical_rcv_bookmarks_fragment, parent, false)
        return BookmarksGenersAdapter.MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item= genersList[position]

        holder.tvGeners.text=item.toString()
    }

    override fun getItemCount(): Int {
       return genersList.size
    }

}