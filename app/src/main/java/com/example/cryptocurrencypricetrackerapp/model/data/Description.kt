package com.example.cryptocurrencypricetrackerapp.model.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Description(
    @SerializedName("en")
    val en: String,
    @SerializedName("tr")
    val tr: String,
): Parcelable {
}