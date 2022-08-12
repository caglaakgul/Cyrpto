package com.example.cryptocurrencypricetrackerapp.di.module

import com.example.cryptocurrencypricetrackerapp.view.detail.DetailFragment
import com.example.cryptocurrencypricetrackerapp.view.favorites.FavoritesFragment
import com.example.cryptocurrencypricetrackerapp.view.home.HomeFragment
import com.example.cryptocurrencypricetrackerapp.view.login.LoginFragment
import com.example.cryptocurrencypricetrackerapp.view.register.RegisterFragment
import com.example.cryptocurrencypricetrackerapp.view.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {
    @ContributesAndroidInjector(modules = arrayOf(ViewModelModule::class))
    abstract fun contributeLoginFragment(): LoginFragment

    @ContributesAndroidInjector(modules = arrayOf(ViewModelModule::class))
    abstract fun contributeRegisterFragment(): RegisterFragment

    @ContributesAndroidInjector(modules = arrayOf(ViewModelModule::class))
    abstract fun contributeHomeFragment(): HomeFragment

    @ContributesAndroidInjector(modules = arrayOf(ViewModelModule::class))
    abstract fun contributeFavoritesFragment(): FavoritesFragment

    @ContributesAndroidInjector(modules = arrayOf(ViewModelModule::class))
    abstract fun contributeSearchFragment(): SearchFragment

    @ContributesAndroidInjector(modules = arrayOf(ViewModelModule::class))
    abstract fun contributeDetailFragment(): DetailFragment

}