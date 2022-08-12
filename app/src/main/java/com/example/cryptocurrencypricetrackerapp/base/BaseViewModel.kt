package com.example.cryptocurrencypricetrackerapp.base

import androidx.lifecycle.ViewModel
import com.example.cryptocurrencypricetrackerapp.util.PrefUtil
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

open class BaseViewModel : ViewModel() {
    var compositeDisposable = CompositeDisposable()

    @Inject
    lateinit var prefUtil: PrefUtil

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
        compositeDisposable = CompositeDisposable()
    }
}