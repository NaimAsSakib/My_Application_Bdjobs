package com.example.myapplicationbdjobs.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationbdjobs.R
import com.example.myapplicationbdjobs.api.models.ResultsItem

class HomePopularRCVAdapter( private val resultItem: ArrayList<ResultsItem>) :
RecyclerView.Adapter<HomePopularRCVAdapter.MyViewHolder>(){
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val cardHomePopular: CardView=itemView.findViewById(R.id.cardVerticalHomeFrag)
        val imageMoviePopular: AppCompatImageView=itemView.findViewById(R.id.ivVerticalMoviePicHomeFrag)
        val tvMovieNamePopular: TextView=itemView.findViewById(R.id.tvVerticalMovieNameHomeFrag)
        val tvMovieRatingPopular: TextView=itemView.findViewById(R.id.tvVerticalRatingHomeFrag)
        val timeMoviePopular: TextView=itemView.findViewById(R.id.tvMovieTimeVerticalHomeFrag)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_vertical_rcv_home_fragment, parent, false)
        return MyViewHolder(itemView)    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item= resultItem[position]

        Glide.with(holder.imageMoviePopular.context)
            .load(item.posterPath)
            .error(R.drawable.demo_movie)
            .into(holder.imageMoviePopular)

        holder.tvMovieNamePopular.text= item.originalTitle
        holder.tvMovieRatingPopular.text= item.voteAverage.toString()
    }



    override fun getItemCount(): Int {
        return resultItem.size
    }
}