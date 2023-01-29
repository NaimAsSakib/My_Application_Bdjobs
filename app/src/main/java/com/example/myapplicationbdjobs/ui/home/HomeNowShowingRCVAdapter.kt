package com.example.myapplicationbdjobs.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationbdjobs.R
import com.example.myapplicationbdjobs.api.models.ResultsItem
import com.example.myapplicationbdjobs.api.models.home.now_showing.ResultsItemNowShowing

class HomeNowShowingRCVAdapter (private val resultsNowShowing: ArrayList<ResultsItemNowShowing> ) : RecyclerView.Adapter<HomeNowShowingRCVAdapter.MyViewHolder>() {

    class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val movieImageNowShowing: ImageView = itemView.findViewById(R.id.ivHorizontalMoviePicHomeFrag)
        val tvMovieNameNowShowing: TextView = itemView.findViewById(R.id.tvHorizontalMovieNameHomeFrag)
        val tvMovieRatingNowShowing: TextView = itemView.findViewById(R.id.tvHorizontalRatingHomeFrag)



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_horizontal_rcv_home_fragment, parent, false)
        return HomeNowShowingRCVAdapter.MyViewHolder(itemView)
    }



    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item= resultsNowShowing[position]

        val imageFirstPart="https://image.tmdb.org/t/p/w500"
        val imageApiPart=item?.posterPath.toString()
        val image= imageFirstPart.trim()+imageApiPart.trim()

        Glide.with(holder.movieImageNowShowing.context)
            .load(image)
            .error(R.drawable.demo_movie)
            .into(holder.movieImageNowShowing)

        holder.tvMovieNameNowShowing.text= item.originalTitle
        holder.tvMovieRatingNowShowing.text=item.voteAverage.toString()
    }
    override fun getItemCount(): Int {
        return resultsNowShowing.size
    }

}