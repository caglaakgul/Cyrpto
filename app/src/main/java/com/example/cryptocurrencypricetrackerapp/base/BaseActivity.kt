package com.example.cryptocurrencypricetrackerapp.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.cryptocurrencypricetrackerapp.util.PrefUtil
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

open class BaseActivity : DaggerAppCompatActivity(){
    val TAG = this.javaClass.name

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var prefUtil: PrefUtil

    fun <T : ViewModel> viewModelOf(c: Class<T>) : T {
        return ViewModelProvider(this, viewModelFactory).get(c)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        AndroidInjection.inject(this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(this.TAG, true)
    }

    fun <T: Activity> intent(activityClass: Class<T>){
        startActivity(Intent(this, activityClass))
    }



}