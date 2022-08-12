package com.example.cryptocurrencypricetrackerapp.util

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject

const val IS_LOGIN = "IS_LOGIN"
const val IS_LOGOUT = "IS_LOGOUT"

class PrefUtil @Inject constructor(app: Application) {
    val sharedPreferences: SharedPreferences = app.getSharedPreferences("com.example.cryptocurrencypricetrackerapp", Context.MODE_PRIVATE)
    var editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun login(gsm:String) {
        editor.apply {
            putBoolean(IS_LOGIN, true)
            putBoolean(IS_LOGOUT, false)
        }.apply()
    }

    fun setIsLogout(isLogout: Boolean) {
        editor.apply {
            putBoolean(IS_LOGOUT, isLogout)
        }.apply()
    }

    fun getIsLogin(): Boolean = sharedPreferences.getBoolean(IS_LOGIN, false)
    fun getIsLogout(): Boolean = sharedPreferences.getBoolean(IS_LOGOUT, false)
}