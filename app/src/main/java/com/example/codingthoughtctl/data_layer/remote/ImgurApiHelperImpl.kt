package com.example.codingthoughtctl.data_layer.remote

import com.example.codingthoughtctl.data_layer.remote.models.ImgurResponce
import retrofit2.Response

class ImgurApiHelperImpl(private val imgurApiService: ImgurApiService) : ImgurApiHelper {
    override suspend fun getTopWeeklyImages(
        section: String,
        sort: String,
        window: String
    ): Response<ImgurResponce> =
        imgurApiService.getTopWeeklyImages(section, sort, window)

    override suspend fun getTopSearchQuery(
        sort: String,
        query: String?
    ): Response<ImgurResponce> = imgurApiService.getTopSearchQuery(sort, query)


}