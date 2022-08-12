package com.example.cryptocurrencypricetrackerapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.cryptocurrencypricetrackerapp.base.BaseViewModel
import com.example.cryptocurrencypricetrackerapp.model.CoinDetailResponse
import com.example.cryptocurrencypricetrackerapp.model.SearchListResponse
import com.example.cryptocurrencypricetrackerapp.service.ApiService
import com.example.cryptocurrencypricetrackerapp.service.call.callCoinDetailRequest
import com.example.cryptocurrencypricetrackerapp.service.call.callSearchListRequest
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val apiService: ApiService) : BaseViewModel()  {
    var coinDetailResponse = MutableLiveData<CoinDetailResponse>()

    val loading = MutableLiveData<Boolean>()

    fun callSearchList(id:String){
        callCoinDetailRequest(apiService, coinDetailResponse,  compositeDisposable, loading, id)
    }
}