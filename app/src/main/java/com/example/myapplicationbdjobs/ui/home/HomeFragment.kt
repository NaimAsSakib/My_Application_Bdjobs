package com.example.myapplicationbdjobs.ui.home

import android.app.Activity
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
import com.example.myapplicationbdjobs.api.models.home.now_showing.ResultsItemNowShowing
import com.example.myapplicationbdjobs.databinding.FragmentHomeBinding
import com.example.myapplicationbdjobs.helper.LoadingProgressBarDialog
import com.example.myapplicationbdjobs.listener.ItemOnClickListener
import com.example.myapplicationbdjobs.ui.MainActivity
import com.google.gson.Gson
import dagger.hilt.android.AndroidEntryPoint
import kotlin.properties.Delegates

@AndroidEntryPoint
class HomeFragment : Fragment() , ItemOnClickListener{
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private lateinit var loadingProgressBarDialog: LoadingProgressBarDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       // loadingProgressBarDialog= LoadingProgressBarDialog(context as Activity)

        navigation()
    }

    //navigation & RCV codes
    private fun navigation(){

        //for opening drawer menu
        binding.drawerIcon.setOnClickListener {
            (activity as MainActivity).openDrawer()
        }


        /*binding.btnHomeFrag.setOnClickListener {   //code to navigate to the defined fragment from navigation graph
            findNavController().navigate(R.id.action_homeFragment_to_detailsFragment)
        }*/

        viewModel.callPopularMovies()

        viewModel.popularMoviesLiveData.observe(viewLifecycleOwner){data->
            data?.let {
                //Toast.makeText(context, Gson().toJson(it), Toast.LENGTH_LONG).show()



                val programAdapter= HomePopularRCVAdapter(it.results as ArrayList<ResultsItem>,this)
                binding.rcvVerticalHomeFragment.adapter=programAdapter


            }
        }

        viewModel.callNowShowingMovies()
        viewModel.nowShowingMoviesLiveData.observe(viewLifecycleOwner){data->
            data?.let {
               // Toast.makeText(context, Gson().toJson(it), Toast.LENGTH_SHORT).show()
                val programAdapterNowShowing= HomeNowShowingRCVAdapter(it.results as ArrayList<ResultsItemNowShowing>, this )
                binding.rcvHorizontalHomeFragment.adapter=programAdapterNowShowing

            }
        }


        viewModel.errorLiveData.observe(viewLifecycleOwner){
            Toast.makeText(context,it, Toast.LENGTH_LONG).show()
        }
    }

    override fun onClickListener(name: String, value: Int) {
        val bundle= Bundle()   //bundle
        bundle.putInt("movieID",value)
        findNavController().navigate(R.id.action_homeFragment_to_detailsFragment,bundle)
    }
}