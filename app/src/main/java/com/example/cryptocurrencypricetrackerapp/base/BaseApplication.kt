package com.example.cryptocurrencypricetrackerapp.base

import com.example.cryptocurrencypricetrackerapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()

        INSTANCE = this

    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

    companion object {
        lateinit var INSTANCE: BaseApplication
    }
}