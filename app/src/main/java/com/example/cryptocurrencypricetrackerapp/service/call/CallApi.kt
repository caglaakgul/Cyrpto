package com.example.cryptocurrencypricetrackerapp.service.call

import androidx.lifecycle.MutableLiveData
import com.example.cryptocurrencypricetrackerapp.model.CoinListResponse
import com.example.cryptocurrencypricetrackerapp.model.CoinDetailResponse
import com.example.cryptocurrencypricetrackerapp.model.SearchListResponse
import com.example.cryptocurrencypricetrackerapp.service.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

fun callCoinListRequest(
    apiService: ApiService,
    coinListResponse: MutableLiveData<ArrayList<CoinListResponse>>,
    disposable: CompositeDisposable,
    loading: MutableLiveData<Boolean>
) {
    loading.value = true
    disposable.add(
        apiService.getCoinList()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<ArrayList<CoinListResponse>>() {
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    loading.value = false
                }

                override fun onSuccess(t: ArrayList<CoinListResponse>) {
                    coinListResponse.value = t
                    loading.value = false
                }
            })
    )
}

fun callSearchListRequest(
    apiService: ApiService,
    response: MutableLiveData<SearchListResponse>,
    disposable: CompositeDisposable,
    loading: MutableLiveData<Boolean>,
    query: String
) {
    loading.value = true
    disposable.add(
        apiService.getSearchList(query)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<SearchListResponse>() {
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    loading.value = false
                }

                override fun onSuccess(t: SearchListResponse) {
                    response.value = t
                    loading.value = false
                }
            })
    )
}

fun callCoinDetailRequest(
    apiService: ApiService,
    response: MutableLiveData<CoinDetailResponse>,
    disposable: CompositeDisposable,
    loading: MutableLiveData<Boolean>,
    id: String
) {
    loading.value = true
    disposable.add(
        apiService.getCoinDetail(id)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<CoinDetailResponse>() {
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    loading.value = false
                }

                override fun onSuccess(t: CoinDetailResponse) {
                    response.value = t
                    loading.value = false
                }
            })
    )
}
