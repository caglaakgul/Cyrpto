package com.example.cryptocurrencypricetrackerapp.model

import android.os.Parcelable
import com.example.cryptocurrencypricetrackerapp.model.data.Coins
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchListResponse(
    @SerializedName("coins")
    val coins: ArrayList<Coins>
): Parcelable {
}