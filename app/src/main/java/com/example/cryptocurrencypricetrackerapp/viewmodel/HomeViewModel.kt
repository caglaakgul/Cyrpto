package com.example.cryptocurrencypricetrackerapp.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.cryptocurrencypricetrackerapp.base.BaseViewModel
import com.example.cryptocurrencypricetrackerapp.model.CoinListResponse
import com.example.cryptocurrencypricetrackerapp.model.CoinDetailResponse
import com.example.cryptocurrencypricetrackerapp.service.ApiService
import com.example.cryptocurrencypricetrackerapp.service.call.callCoinListRequest
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val apiService: ApiService) : BaseViewModel()  {
    var coinListResponse = MutableLiveData<ArrayList<CoinListResponse>>()

    val loading = MutableLiveData<Boolean>()

    fun callCoinList(){
        callCoinListRequest(apiService, coinListResponse,  compositeDisposable, loading)
    }

}