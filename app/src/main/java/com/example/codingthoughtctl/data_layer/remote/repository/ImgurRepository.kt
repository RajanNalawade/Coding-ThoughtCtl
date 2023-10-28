package com.example.codingthoughtctl.data_layer.remote.repository

import com.example.codingthoughtctl.data_layer.remote.ImgurApiHelper
import com.example.codingthoughtctl.data_layer.remote.models.ImgurResponce
import retrofit2.Response
import javax.inject.Inject

class ImgurRepository @Inject constructor(
    private val imgurApiHelper: ImgurApiHelper
) {
    suspend fun getTopWeeklyImages(
        section: String,
        sort: String,
        window: String
    ): Response<ImgurResponce> = imgurApiHelper.getTopWeeklyImages(section, sort, window)

    suspend fun getTopSearchQuery(
        sort: String,
        query: String?
    ): Response<ImgurResponce> = imgurApiHelper.getTopSearchQuery(sort, query)
}