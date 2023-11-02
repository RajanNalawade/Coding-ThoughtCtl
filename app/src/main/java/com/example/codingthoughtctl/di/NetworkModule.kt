package com.example.codingthoughtctl.di

import android.content.Context
import com.example.codingthoughtctl.data_layer.remote.ImgurApiHelper
import com.example.codingthoughtctl.data_layer.remote.ImgurApiHelperImpl
import com.example.codingthoughtctl.data_layer.remote.ImgurApiService
import com.example.codingthoughtctl.utilities.Keys
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Singleton
    @Provides
    fun providesRetrofitBuilder(): Retrofit.Builder =
        Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())

    @Singleton
    @Provides
    fun provideOkHttpClient(@ApplicationContext context: Context): OkHttpClient {

        val cacheSize = (5 * 1024 * 1024).toLong()
        val myCache = Cache(context.cacheDir, cacheSize)

        return OkHttpClient.Builder()
            .addInterceptor {
                val requestBuilder = it.request().newBuilder()
                requestBuilder.addHeader("Authorization", "Bearer ${Keys.getAssessToken()}")
                it.proceed(requestBuilder.build())
            }.cache(myCache).build()
    }

    @Singleton
    @Provides
    fun provideImgurApiService(
        retrofitBuilder: Retrofit.Builder,
        okHttpClient: OkHttpClient
    ): ImgurApiService {
        return retrofitBuilder.client(okHttpClient).baseUrl(Keys.getBaseUrl()).build()
            .create(ImgurApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideImgurApiHelper(imgurApiService: ImgurApiService): ImgurApiHelper =
        ImgurApiHelperImpl(imgurApiService)
}