package com.example.myapplicationbdjobs.ui.bookmarks

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationbdjobs.R
import com.example.myapplicationbdjobs.api.models.AppTable
import com.example.myapplicationbdjobs.api.models.details.GenresItem
import com.example.myapplicationbdjobs.listener.DeleteListener

class BookmarkAdapter (private val arrayList: ArrayList<AppTable>, private val listener: DeleteListener) :
RecyclerView.Adapter<BookmarkAdapter.MyViewHolder>(){

    class MyViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val ivMovieImage: ImageView= itemView.findViewById(R.id.ivPictureRCvBookmarks)
        val tvMovieName: TextView= itemView.findViewById(R.id.tvMovieNameBookmarksFrag)
        val tvRating: TextView= itemView.findViewById(R.id.tvVerticalBookmarksFrag)
        val tvMovieTime: TextView= itemView.findViewById(R.id.tvMovieTimeVerticalDeleteFrag)
        val ivDelete: ImageView= itemView.findViewById(R.id.ivDeleteBookMarks)

        private val strBuilder= java.lang.StringBuilder()
        val rcvNestedAdapterGeners: RecyclerView=itemView.findViewById(R.id.rcvHorizontalBookmarksFragment)
        lateinit var geners : List<String>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.custom_vertical_rcv_bookmarks_fragment, parent, false)
        return BookmarkAdapter.MyViewHolder(itemView)
    }


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item= arrayList[position]

        holder.tvMovieName.text=item.originalTitle
        holder.tvRating.text=item.voteAverage
        holder.tvMovieTime.text= item.runtime?.let { minuteToTime(it) }

        val imageFirstPart="https://image.tmdb.org/t/p/w500"
        val imageApiPart=item?.posterPath.toString()
        val image= imageFirstPart.trim()+imageApiPart.trim()

        Glide.with(holder.ivMovieImage.context)
            .load(image)
           // .error(R.drawable.demo_movie)
            .into(holder.ivMovieImage)

        holder.ivDelete.setOnClickListener {
            listener.onDelete(item)
        }

        holder.geners= item.geners?.split(",")!!


        val bookmarkGenersAdapter= BookmarksGenersAdapter(holder.geners as List<String>)
        holder.rcvNestedAdapterGeners.adapter=bookmarkGenersAdapter
    }

    override fun getItemCount(): Int {
       return arrayList.size
    }

    fun minuteToTime(minute: Int): String? {
        var minute = minute
        var hour = minute / 60
        minute %= 60
        var p = "AM"
        if (hour >= 12) {
            hour %= 12
            p = "PM"
        }
        if (hour == 0) {
            hour = 12
        }
        return (if (hour < 10) "$hour" else hour).toString() + "h " + (if (minute < 10) "$minute" else minute) +"m"
    }
}