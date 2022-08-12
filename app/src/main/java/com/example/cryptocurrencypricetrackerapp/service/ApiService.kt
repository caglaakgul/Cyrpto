package com.example.cryptocurrencypricetrackerapp.service

import com.example.cryptocurrencypricetrackerapp.model.CoinListResponse
import com.example.cryptocurrencypricetrackerapp.model.CoinDetailResponse
import com.example.cryptocurrencypricetrackerapp.model.SearchListResponse
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    @GET("coins/list")
    fun getCoinList(@Query("include_platform") includePlatform: Boolean = false): Single<ArrayList<CoinListResponse>>

    @GET("coins/{id}")
    fun getCoinDetail(@Path("id") id:String): Single<CoinDetailResponse>

    @GET("search")
    fun getSearchList(@Query("query") query: String): Single<SearchListResponse>

}