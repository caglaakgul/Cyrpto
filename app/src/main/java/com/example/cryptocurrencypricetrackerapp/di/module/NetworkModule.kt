package com.example.cryptocurrencypricetrackerapp.di.module

import android.app.Application
import com.example.cryptocurrencypricetrackerapp.BuildConfig
import com.example.cryptocurrencypricetrackerapp.service.ApiService
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

@Module
object NetworkModule {

    @Provides
    @JvmStatic
    internal fun provideApi(client: OkHttpClient): ApiService {
        val gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.API_HOST)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @JvmStatic
    fun provideOkHttpClient(application: Application): OkHttpClient {
        val okhttp = OkHttpClient.Builder()
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)

        return okhttp.build()
    }


}