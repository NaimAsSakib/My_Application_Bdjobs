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
import com.example.myapplicationbdjobs.databinding.FragmentDetailsBinding
import com.example.myapplicationbdjobs.databinding.FragmentHomeBinding
import com.example.myapplicationbdjobs.ui.home.HomeViewModel
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val viewModel: DetailsViewModel by viewModels()

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

        viewModel.callDetailsMovie()

        viewModel.detailsMovieLiveData.observe(viewLifecycleOwner){data->
            data?.let {
               // Toast.makeText(context, Gson().toJson(it), Toast.LENGTH_LONG).show()

                binding.tvMovieName.text= data.originalTitle
                binding.tvRatingResult.text=data.voteAverage.toString()
                binding.tvLengthTime.text= data.runtime.toString()
                binding.tvLanguageType.text=data.originalLanguage

                binding.descriptionDetailsMsg.text=data.overview

                //movie picture load code
                val imageFirstPart="https://image.tmdb.org/t/p/w500"
                val imageApiPart=data.posterPath
                val image= imageFirstPart.trim()+imageApiPart
                Glide.with(binding.tvMovieName.context)
                    .load(image)
                    .error(R.drawable.demo_movie)
                    .into(binding.ivMovieImage)

            }

        }
    }
}