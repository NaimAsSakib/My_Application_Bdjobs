package com.example.myapplicationbdjobs.ui.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.myapplicationbdjobs.R
import com.example.myapplicationbdjobs.api.models.AppTable
import com.example.myapplicationbdjobs.api.models.details.DetailsResponse
import com.example.myapplicationbdjobs.api.models.details.GenresItem
import com.example.myapplicationbdjobs.databinding.FragmentDetailsBinding
import com.example.myapplicationbdjobs.databinding.FragmentHomeBinding
import com.example.myapplicationbdjobs.ui.home.HomeViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()
    private var detailsResponse:DetailsResponse?=null

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

        viewModel.getDbData()
        viewModel.check(movieId?:0)

        viewModel.detailsMovieLiveData.observe(viewLifecycleOwner){data->
            data?.let {it->

                detailsResponse=it

                // Toast.makeText(context, Gson().toJson(it), Toast.LENGTH_LONG).show()

                binding.tvMovieName.text= data.originalTitle
                binding.tvMovieRatingDetails.text= data.voteAverage.toString()
                binding.tvLengthTime.text= data.runtime.toString()
                binding.tvLanguageType.text=data.originalLanguage

                binding.descriptionDetailsMsg.text=data.overview


                for (i in data.genres!!){
                    if (i != null) {
                        strBuilder.append(i.name+",")
                    }
                }

                //movie picture load code
                val imageFirstPart="https://image.tmdb.org/t/p/w500"
                val imageApiPart=data.posterPath
                val image= imageFirstPart.trim()+imageApiPart
                Glide.with(binding.tvMovieName.context)
                    .load(image)
                    .error(R.drawable.demo_movie)
                    .into(binding.ivMovieImage)





                //for saving in bookmark
                binding.ivSaveInBookmarks.setOnClickListener {view->
                    detailsResponse?.let {
                        val appTable=AppTable(
                            id=it.id,
                            originalLanguage = it.originalLanguage,
                            originalTitle = it.originalTitle,
                            voteAverage = it.voteAverage.toString(),
                            runtime = it.runtime,
                            posterPath = it.posterPath,
                            geners = strBuilder.toString()
                        )
                        viewModel.addBookmarks(appTable)
                        Log.e("msg","msg"+ appTable)
                    }

                }


            }

        }


           /* viewModel.callGeners()

        viewModel.detailsMovieLiveDataGeners.observe(viewLifecycleOwner){data->
            data?.let {
                val programAdapterForGeners= DetailsGenersAdapter(it.name as ArrayList<GenresItem>)
                binding.rcvHorizontalDetailsFragment.adapter=programAdapterForGeners
            }

        }*/

        viewModel.errorLiveData.observe(viewLifecycleOwner){
            Toast.makeText(context,it, Toast.LENGTH_LONG).show()
        }




    }
}