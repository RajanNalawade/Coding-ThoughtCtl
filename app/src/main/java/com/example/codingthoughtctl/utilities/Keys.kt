package com.example.codingthoughtctl.utilities

object Keys {

    init {
        System.loadLibrary("codingthoughtctl")
    }

    external fun getAssessToken(): String

    external fun getClientID(): String

    external fun getClientSecret(): String

    external fun getBaseUrl(): String

    external fun getAccessTokenUrl(): String

    external fun getRefreshToken(): String
}