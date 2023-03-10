package com.example.myapplicationbdjobs.ui.details

import android.os.Bundle
import android.util.Log
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
import com.example.myapplicationbdjobs.helper.Helper
import com.example.myapplicationbdjobs.helper.LoadingProgressBarDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    private var detailsResponse:DetailsResponse?=null

    private val strBuilder= java.lang.StringBuilder()  //for splitting geners

    private lateinit var loadingProgressBarDialog: LoadingProgressBarDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingProgressBarDialog= LoadingProgressBarDialog(requireActivity())

        val movieId= arguments?.getInt("movieID")   //getting that movie Id from Home fragment when any item is clicked


        if (movieId != null) {
            viewModel.callDetailsMovie(movieId)
        }

        viewModel.check(movieId?:0)

        loadingProgressBarDialog.startProgressBarLoading()

        viewModel.detailsMovieLiveData.observe(viewLifecycleOwner){data->
            data?.let {it->

                detailsResponse=it

                binding.tvMovieName.text= data.originalTitle
                binding.tvMovieRatingDetails.text= data.voteAverage.toString()

                //Converting movie run time from minutes to hour & minute with companion obj (static method) from helper class
                val formattedTime= data.runtime?.let { it1 -> Helper.minuteToTime(it1) }
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

                bookmarksIconUpdate(data.isBookmarked)    //condition for checking isBookmarked value & setting icon accordingly
              //  Log.e("msg","value in observe"+data.isBookmarked)

                loadingProgressBarDialog.dismissProgressBarDialog()

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
                    geners = strBuilder.toString().substring(0,strBuilder.length-1)
                )
               // viewModel.addBookmarks(appTable)

                if (it.isBookmarked) {
                 //   Log.e("msg","value in icon click "+it.isBookmarked)
                    viewModel.deleteBookmarks(appTable)
                    bookmarksIconUpdate(false)
                    it.isBookmarked=false
                } else {
                    viewModel.addBookmarks(appTable)
                  //  Log.e("msg","value in icon click 2  "+it.isBookmarked)
                    bookmarksIconUpdate(true)
                    it.isBookmarked=true
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

}