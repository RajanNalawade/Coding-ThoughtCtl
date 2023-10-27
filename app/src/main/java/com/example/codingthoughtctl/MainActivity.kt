package com.example.codingthoughtctl

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.codingthoughtctl.utilities.Keys

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d("Keys", "access token ${Keys.getAssessToken()}")
        Log.d("Keys", "refresh token ${Keys.getRefreshToken()}")
        Log.d("Keys", "Client ID ${Keys.getClientID()}")
        Log.d("Keys", "Client secret ${Keys.getClientSecret()}")
        Log.d("Keys", "base url ${Keys.getBaseUrl()}")
        Log.d("Keys", "access token url ${Keys.getAccessTokenUrl()}")
    }
}