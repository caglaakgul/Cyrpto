package com.example.cryptocurrencypricetrackerapp.model.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Platforms(
    val ethereum: String
): Parcelable {
}