package com.example.codingthoughtctl.ui_layer.home

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.codingthoughtctl.R
import com.example.codingthoughtctl.data_layer.remote.models.ImgurResponce
import com.example.codingthoughtctl.utilities.Keys
import com.example.codingthoughtctl.utilities.NetworkResult
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    val viewModel: HomeViewModel by viewModels<HomeViewModel>()

    private lateinit var binding: Bindin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Log.d("Keys", "access token ${Keys.getAssessToken()}")
        Log.d("Keys", "refresh token ${Keys.getRefreshToken()}")
        Log.d("Keys", "Client ID ${Keys.getClientID()}")
        Log.d("Keys", "Client secret ${Keys.getClientSecret()}")
        Log.d("Keys", "base url ${Keys.getBaseUrl()}")
        Log.d("Keys", "access token url ${Keys.getAccessTokenUrl()}")

        viewModel.topImagesResult.observe(this@HomeActivity, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    Log.d(
                        "Keys",
                        "success: ${(it as NetworkResult.Success<ImgurResponce>).data?.data?.size}"
                    )
                }

                is NetworkResult.Error -> {
                    Log.d(
                        "Keys",
                        "error: ${(it as NetworkResult.Error<ImgurResponce>).message}"
                    )
                }

                is NetworkResult.Loading -> {
                    Log.d("Keys", "loading: loading")
                }

                else -> {
                    Log.d("Keys", "else: else branch")
                }
            }
        })

        viewModel.topSearchResult.observe(this@HomeActivity, Observer {
            when (it) {
                is NetworkResult.Success -> {
                    Log.d(
                        "Keys",
                        "search success: ${(it as NetworkResult.Success<ImgurResponce>).data?.data?.size}"
                    )
                }

                is NetworkResult.Error -> {
                    Log.d(
                        "Keys",
                        "search error: ${(it as NetworkResult.Error<ImgurResponce>).message}"
                    )
                }

                is NetworkResult.Loading -> {
                    Log.d("Keys", "search loading: loading")
                }

                else -> {
                    Log.d("Keys", "search else: else branch")
                }
            }
        })
    }
}