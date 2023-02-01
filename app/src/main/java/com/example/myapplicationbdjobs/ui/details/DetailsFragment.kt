package com.example.myapplicationbdjobs.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.myapplicationbdjobs.R
import com.example.myapplicationbdjobs.api.models.AppTable
import com.example.myapplicationbdjobs.api.models.details.DetailsResponse
import com.example.myapplicationbdjobs.api.models.details.GenresItem
import com.example.myapplicationbdjobs.databinding.FragmentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    private var detailsResponse:DetailsResponse?=null

   // private  var isBookmared=false

    private val strBuilder= java.lang.StringBuilder()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val movieId= arguments?.getInt("movieID")   //getting that movie Id from Home fragment when any item is clicked


        if (movieId != null) {
            viewModel.callDetailsMovie(movieId)
        }

        viewModel.check(movieId?:0)

        viewModel.detailsMovieLiveData.observe(viewLifecycleOwner){data->
            data?.let {it->

                detailsResponse=it

                binding.tvMovieName.text= data.originalTitle
                binding.tvMovieRatingDetails.text= data.voteAverage.toString()

                val formattedTime= data.runtime?.let { it1 -> minuteToTime(it1) }
                binding.tvLengthTime.text= formattedTime

                binding.tvLanguageType.text=data.originalLanguage

                binding.descriptionDetailsMsg.text=data.overview


                for (i in data.genres!!){
                    if (i != null) {
                        strBuilder.append(i.name+",")
                    }
                }

                //recyclerview for showing geners
                binding.rcvHorizontalDetailsFragment.adapter=DetailsGenersAdapter(it.genres as ArrayList<GenresItem>)


                //movie picture load code
                val imageFirstPart="https://image.tmdb.org/t/p/w500"
                val imageApiPart=data.posterPath
                val image= imageFirstPart.trim()+imageApiPart
                Glide.with(binding.tvMovieName.context)
                    .load(image)
                    .into(binding.ivMovieImage)

                bookmarksIconUpdate(data.isBookmarked)
            }
        }

        //for saving in bookmark
        binding.ivSaveInBookmarks.setOnClickListener {view->
            detailsResponse?.let {
                val appTable = AppTable(
                    id = it.id,
                    originalLanguage = it.originalLanguage,
                    originalTitle = it.originalTitle,
                    voteAverage = it.voteAverage.toString(),
                    runtime = it.runtime,
                    posterPath = it.posterPath,
                    geners = strBuilder.toString()
                )
               // viewModel.addBookmarks(appTable)

                if (it.isBookmarked) {
                    viewModel.deleteBookmarks(appTable)
                    bookmarksIconUpdate(false)
                } else {
                    viewModel.addBookmarks(appTable)
                    bookmarksIconUpdate(true)
                }
            }

        }

        viewModel.errorLiveData.observe(viewLifecycleOwner){
            Toast.makeText(context,it, Toast.LENGTH_LONG).show()
        }

    }


    private fun bookmarksIconUpdate(isBookmark: Boolean){

        if (isBookmark){
            binding.ivSaveInBookmarks.setImageResource(R.drawable.baseline_bookmark_selected)
        }else{
            binding.ivSaveInBookmarks.setImageResource(R.drawable.baseline_bookmark_border_24)
        }
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