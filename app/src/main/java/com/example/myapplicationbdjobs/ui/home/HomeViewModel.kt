package com.example.myapplicationbdjobs.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationbdjobs.api.models.PopularResponse
import com.example.myapplicationbdjobs.repository.AppRepository
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val appRepository: AppRepository) :ViewModel() {

    private val _popularMoviesLiveData = MutableLiveData<PopularResponse?>()
    val popularMoviesLiveData: LiveData<PopularResponse?>
        get() = _popularMoviesLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData

    private val _nowShowingLiveData = MutableLiveData<PopularResponse?>()
    val nowShowingMoviesLiveData: LiveData<PopularResponse?>
        get() = _nowShowingLiveData

    fun callPopularMovies(){

        viewModelScope.launch {

            val response=appRepository.getPopularMovie()

            when(response){
                is NetworkResponse.Success -> {
                    _popularMoviesLiveData.value=response.body
                }
                is NetworkResponse.ServerError -> {
                    _errorLiveData.value= response.body?.statusMessage ?:("something went wrong")
                }
                is NetworkResponse.NetworkError -> {
                    _errorLiveData.value="No internet"
                }
                is NetworkResponse.UnknownError -> {
                    _errorLiveData.value="something went wrong"
                }
            }
        }
    }



}