package com.example.cryptocurrencypricetrackerapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.cryptocurrencypricetrackerapp.base.BaseViewModel
import com.example.cryptocurrencypricetrackerapp.model.SearchListResponse
import com.example.cryptocurrencypricetrackerapp.service.ApiService
import com.example.cryptocurrencypricetrackerapp.service.call.callSearchListRequest
import javax.inject.Inject

class SearchViewModel @Inject constructor(private val apiService: ApiService) : BaseViewModel()  {
    var searchListResponse = MutableLiveData<SearchListResponse>()

    val loading = MutableLiveData<Boolean>()

    fun callSearchList(query:String){
        callSearchListRequest(apiService, searchListResponse,  compositeDisposable, loading, query)
    }
}