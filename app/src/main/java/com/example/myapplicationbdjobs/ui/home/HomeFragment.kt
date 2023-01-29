package com.example.myapplicationbdjobs.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.myapplicationbdjobs.R
import com.example.myapplicationbdjobs.api.models.ResultsItem
import com.example.myapplicationbdjobs.databinding.FragmentHomeBinding
import com.example.myapplicationbdjobs.ui.MainActivity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnHomeFrag.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)
        }


        viewModel.callPopularMovies()



        viewModel.popularMoviesLiveData.observe(viewLifecycleOwner){data->
            data?.let {
                Log.e("msg ","data "+data)
                //Toast.makeText(context, Gson().toJson(it), Toast.LENGTH_LONG).show()
                val programAdapter= HomePopularRCVAdapter(it.results as ArrayList<ResultsItem>)
                binding.rcvVerticalHomeFragment.adapter=programAdapter
            }
        }

        viewModel.nowShowingMoviesLiveData.observe(viewLifecycleOwner){
            //add adapter for vertical rcv
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner){
            Toast.makeText(context,it, Toast.LENGTH_LONG).show()
        }
    }


}