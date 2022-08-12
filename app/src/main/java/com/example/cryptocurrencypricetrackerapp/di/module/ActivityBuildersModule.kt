package com.example.cryptocurrencypricetrackerapp.di.module


import com.example.cryptocurrencypricetrackerapp.view.MainActivity
import com.example.cryptocurrencypricetrackerapp.view.WelcomeActivity
import com.example.cryptocurrencypricetrackerapp.view.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class, ViewModelModule::class])
    abstract fun contributeMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class, ViewModelModule::class])
    abstract fun contributeSplashActivity(): SplashActivity

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class, ViewModelModule::class])
    abstract fun contributeWelcomeActivity(): WelcomeActivity
}