package com.example.cryptocurrencypricetrackerapp.di

import android.app.Application
import com.example.cryptocurrencypricetrackerapp.base.BaseApplication
import com.example.cryptocurrencypricetrackerapp.di.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuildersModule::class,
        AppModule::class,
        ViewModelFactoryModule::class,
        NetworkModule::class,
        FragmentBuildersModule::class
    ]
)

interface AppComponent : AndroidInjector<BaseApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}