package com.example.codingthoughtctl.data_layer.remote.models


import com.google.gson.annotations.SerializedName

data class Processing(
    @SerializedName("status")
    val status: String?
)