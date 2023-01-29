package com.example.myapplicationbdjobs.ui.home

import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplicationbdjobs.R
import com.example.myapplicationbdjobs.api.models.ResultsItem

class HomeNowShowingRCVAdapter () : RecyclerView.Adapter<HomeNowShowingRCVAdapter.MyViewHolder>() {

    class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val cardViewNowShowing: CardView = itemView.findViewById(R.id.cardHorizontalHomeFrag)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}