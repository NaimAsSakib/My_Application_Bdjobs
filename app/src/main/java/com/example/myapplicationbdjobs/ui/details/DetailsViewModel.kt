package com.example.myapplicationbdjobs.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationbdjobs.api.models.details.DetailsResponse
import com.example.myapplicationbdjobs.repository.AppRepository
import com.haroldadmin.cnradapter.NetworkResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel() {

    private val _detailsMovieLiveData = MutableLiveData<DetailsResponse?>()
    val detailsMovieLiveData: LiveData<DetailsResponse?>
    get()= _detailsMovieLiveData

    private val _errorLiveData = MutableLiveData<String>()
    val errorLiveData: LiveData<String>
        get() = _errorLiveData

    fun callDetailsMovie(){

        viewModelScope.launch {
            val response= appRepository.getDetailsMovie()

            when(response){
                is NetworkResponse.Success -> {
                    _detailsMovieLiveData.value= response.body
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