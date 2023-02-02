package com.example.myapplicationbdjobs.ui.bookmarks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationbdjobs.api.models.AppTable
import com.example.myapplicationbdjobs.api.models.details.DetailsResponse
import com.example.myapplicationbdjobs.repository.AppRepository
import com.example.myapplicationbdjobs.ui.details.DetailsViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(private val appRepository: AppRepository): ViewModel() {

    private val _bookmarkLiveData = MutableLiveData<List<AppTable>>()
    val bookmarkLiveData: LiveData<List<AppTable>>
        get()= _bookmarkLiveData

    fun getDbData(){
        viewModelScope.launch {
            val res= appRepository.getDbData()
            _bookmarkLiveData.value=res
        }
    }

    fun deleteBookmark(appTable: AppTable){
        viewModelScope.launch {
            appRepository.deleteBookmark(appTable)
        }
    }

}