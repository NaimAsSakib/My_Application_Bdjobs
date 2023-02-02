package com.example.myapplicationbdjobs.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationbdjobs.R
import com.example.myapplicationbdjobs.api.models.ResultsItem
import com.example.myapplicationbdjobs.listener.ItemOnClickListener


class HomePopularRCVAdapter(private val results: ArrayList<ResultsItem>, private val listener: ItemOnClickListener) :
    RecyclerView.Adapter<HomePopularRCVAdapter.MyViewHolder>() {

    class MyViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView){
        val layout: ConstraintLayout= itemView.findViewById(R.id.layoutVerticalHomeFrag)
        val movieImagePopular: ImageView = itemView.findViewById(R.id.ivVerticalMoviePicHomeFrag)
        val tvMovieNamePopular: TextView =itemView.findViewById(R.id.tvVerticalMovieNameHomeFrag)
        val tvRatingPopular: TextView =itemView.findViewById(R.id.tvVerticalRatingHomeFrag)
        val tvMovieTimePopular: TextView =itemView.findViewById(R.id.tvMovieTimeVerticalHomeFrag)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_vertical_rcv_home_fragment, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

         val item= results[position]

        val imageFirstPart="https://image.tmdb.org/t/p/w500"
        val imageApiPart=item?.posterPath.toString()
        val image= imageFirstPart.trim()+imageApiPart.trim()

         Glide.with(holder.movieImagePopular.context)
             .load(image)
             //.error(R.drawable.demo_movie)
             .into(holder.movieImagePopular)

         holder.tvMovieNamePopular.text= item.originalTitle
         holder.tvRatingPopular.text=item.voteAverage.toString()

        holder.layout.setOnClickListener {
            val movieIdFromPopular= item.id

            if (movieIdFromPopular != null) {
                listener.onClickListener("movieIdFromPopular", movieIdFromPopular)
            }
        }


    }

    override fun getItemCount(): Int {
        return results.size
    }

}