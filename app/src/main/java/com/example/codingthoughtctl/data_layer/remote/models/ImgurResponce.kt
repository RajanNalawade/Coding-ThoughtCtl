package com.example.codingthoughtctl.data_layer.remote.models


import com.google.gson.annotations.SerializedName

data class ImgurResponce(
    @SerializedName("data")
    val `data`: List<Data>?,
    @SerializedName("status")
    val status: Int?,
    @SerializedName("success")
    val success: Boolean?
)