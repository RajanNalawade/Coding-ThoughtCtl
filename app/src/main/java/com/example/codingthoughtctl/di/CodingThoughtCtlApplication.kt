package com.example.codingthoughtctl.di

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class CodingThoughtCtlApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        var instance: CodingThoughtCtlApplication? = null
            private set

        @JvmStatic
        val appContext: Context
            get() = instance!!.applicationContext
    }
}