package com.example.cryptocurrencypricetrackerapp.model

import android.os.Parcelable
import com.example.cryptocurrencypricetrackerapp.model.data.Description
import com.example.cryptocurrencypricetrackerapp.model.data.Image
import com.example.cryptocurrencypricetrackerapp.model.data.Platforms
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CoinDetailResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("asset_platform_id")
    val asset_platform_id: String,
    @SerializedName("platforms")
    val platforms: Platforms,
    @SerializedName("description")
    val description: Description,
    @SerializedName("image")
    val image: Image,
    ) : Parcelable {
}