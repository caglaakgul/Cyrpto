package com.example.cryptocurrencypricetrackerapp.di.module

import androidx.lifecycle.ViewModelProvider
import com.example.cryptocurrencypricetrackerapp.di.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}