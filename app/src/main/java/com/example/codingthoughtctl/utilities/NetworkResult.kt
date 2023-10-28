package com.example.codingthoughtctl.utilities

sealed class NetworkResult<T>(val data: T? = null, val message: String? = null) {

    data class Success<T>(val output: T) : NetworkResult<T>(output)

    data class Error<T>(val output: T? = null, val errorMessage: String? = null) :
        NetworkResult<T>(output, errorMessage)

    class Loading<T> : NetworkResult<T>()
}