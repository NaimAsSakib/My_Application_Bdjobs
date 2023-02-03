package com.example.myapplicationbdjobs.ui.bookmarks

import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplicationbdjobs.R
import com.example.myapplicationbdjobs.api.models.AppTable
import com.example.myapplicationbdjobs.helper.Helper
import com.example.myapplicationbdjobs.listener.DeleteListener

class BookmarkAdapter (private val arrayList: ArrayList<AppTable>, private val listener: DeleteListener) :
RecyclerView.Adapter<BookmarkAdapter.MyViewHolder>(){

    class MyViewHolder (itemView: View): RecyclerView.ViewHolder(itemView){
        val ivMovieImage: ImageView= itemView.findViewById(R.id.ivPictureRCvBookmarks)
        val tvMovieName: TextView= itemView.findViewById(R.id.tvMovieNameBookmarksFrag)
        val tvRating: TextView= itemView.findViewById(R.id.tvVerticalBookmarksFrag)
        val tvMovieTime: TextView= itemView.findViewById(R.id.tvMovieTimeVerticalDeleteFrag)
        val ivDelete: ImageView= itemView.findViewById(R.id.ivDeleteBookMarks)

        val rcvNestedAdapterGeners: RecyclerView=itemView.findViewById(R.id.rcvHorizontalBookmarksFragment)
        lateinit var genersList : List<String>
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
        //Converting movie run time from minutes to hour & minute with companion obj (static method) from helper class
        holder.tvMovieTime.text= item.runtime?.let { Helper.minuteToTime(it) }

        val imageFirstPart="https://image.tmdb.org/t/p/w500"
        val imageApiPart=item?.posterPath.toString()
        val image= imageFirstPart.trim()+imageApiPart.trim()

        Glide.with(holder.ivMovieImage.context)
            .load(image)
           // .error(R.drawable.demo_movie)
            .into(holder.ivMovieImage)

        holder.ivDelete.setOnClickListener {

            //Showing alertdialog if user want to delete movie from bookmark or not. If yes, then sending command through listener to delete from appTable
            val builder= AlertDialog.Builder(holder.ivDelete.context)
            val view= LayoutInflater.from(holder.ivDelete.context).inflate(R.layout.alert_dialog_deleteorsave_bookmark, null)
            builder.setView(view)
            val dialog=builder.create()

            view.findViewById<TextView>(R.id.tvAlertDialogYes).setOnClickListener {
                listener.onDelete(item)   //sending command through listener to delete from appTable

                dialog.dismiss()
            }

            view.findViewById<TextView>(R.id.tvAlertDialogNo).setOnClickListener {
                dialog.dismiss()
            }
            if (dialog.window != null){
                dialog.window!!.setBackgroundDrawable(ColorDrawable(0))
            }
            dialog.show()

        }

        holder.genersList= item.geners?.split(",")!!  //splitting that geners & keeping in arrayList

        val bookmarkGenersAdapter= BookmarksGenersAdapter(holder.genersList)
        holder.rcvNestedAdapterGeners.adapter=bookmarkGenersAdapter
    }

    override fun getItemCount(): Int {
       return arrayList.size
    }
}