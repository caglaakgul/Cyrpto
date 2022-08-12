package com.example.cryptocurrencypricetrackerapp.view.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatDelegate
import com.example.cryptocurrencypricetrackerapp.R
import com.example.cryptocurrencypricetrackerapp.base.BaseActivity
import com.example.cryptocurrencypricetrackerapp.view.MainActivity
import com.example.cryptocurrencypricetrackerapp.view.WelcomeActivity

class SplashActivity : BaseActivity() {
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        delegate.localNightMode = AppCompatDelegate.MODE_NIGHT_NO
    }

    private val runnable = Runnable {
        if(!isFinishing){
            intent(WelcomeActivity::class.java)
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 1000)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
}