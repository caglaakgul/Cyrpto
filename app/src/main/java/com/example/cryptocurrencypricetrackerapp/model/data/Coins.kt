package com.example.cryptocurrencypricetrackerapp.model.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Coins(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("market_cap_rank")
    val market_cap_rank: String,
    @SerializedName("thumb")
    val thumb: String,
    @SerializedName("large")
    val large: String,
): Parcelable {
}