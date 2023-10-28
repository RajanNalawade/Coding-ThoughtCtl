package com.example.codingthoughtctl.data_layer.remote.models


import com.google.gson.annotations.SerializedName

data class AdConfig(
    /*@SerializedName("highRiskFlags")
    val highRiskFlags: List<Any?>?,*/
    @SerializedName("high_risk_flags")
    val highRiskFlags: List<Any?>?,
    @SerializedName("nsfw_score")
    val nsfwScore: Double?,
    /*@SerializedName("safeFlags")
    val safeFlags: List<String?>?,*/
    @SerializedName("safe_flags")
    val safeFlags: List<String?>?,
    /*@SerializedName("showAdLevel")
    val showAdLevel: Int?,*/
    @SerializedName("show_ad_level")
    val showAdLevel: Int?,
    @SerializedName("show_ads")
    val showAds: Boolean?,
    /*@SerializedName("showsAds")
    val showsAds: Boolean?,*/
    /*@SerializedName("unsafeFlags")
    val unsafeFlags: List<String?>?,*/
    @SerializedName("unsafe_flags")
    val unsafeFlags: List<String?>?,
    /*@SerializedName("wallUnsafeFlags")
    val wallUnsafeFlags: List<String?>?,*/
    @SerializedName("wall_unsafe_flags")
    val wallUnsafeFlags: List<String?>?
)