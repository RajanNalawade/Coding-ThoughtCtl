package com.example.codingthoughtctl.data_layer.remote

import com.example.codingthoughtctl.data_layer.remote.models.ImgurResponce
import retrofit2.Response

interface ImgurApiHelper {
    suspend fun getTopWeeklyImages(
        section: String,
        sort: String,
        window: String
    ): Response<ImgurResponce>

    /*suspend fun getTopSearchQuery(
        sort: String,
        query: String?
    ): Response<ImgurResponce>*/
}