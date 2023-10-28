package com.example.codingthoughtctl.data_layer.remote

import com.example.codingthoughtctl.data_layer.remote.models.ImgurResponce
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface ImgurApiService {
    @GET("gallery/{section}/{sort}/{window}/")
    suspend fun getTopWeeklyImages(
        @Path("section") section: String, @Path("sort") sort: String, @Path("window") window: String
    ): Response<ImgurResponce>

    @GET("gallery/{sort}/")
    suspend fun getTopSearchQuery(
        @Path("sort") sort: String, @Query("q") query: String?
    ): Response<ImgurResponce>
}