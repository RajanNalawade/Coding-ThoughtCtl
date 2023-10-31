package com.example.codingthoughtctl.ui_layer.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.codingthoughtctl.R
import com.example.codingthoughtctl.databinding.ActivityHomeBinding
import com.example.codingthoughtctl.utilities.Keys
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavigation()

        Log.d("Keys", "access token ${Keys.getAssessToken()}")
        Log.d("Keys", "refresh token ${Keys.getRefreshToken()}")
        Log.d("Keys", "Client ID ${Keys.getClientID()}")
        Log.d("Keys", "Client secret ${Keys.getClientSecret()}")
        Log.d("Keys", "base url ${Keys.getBaseUrl()}")
        Log.d("Keys", "access token url ${Keys.getAccessTokenUrl()}")

        /*viewModel.topSearchResult.observe(this@HomeActivity, Observer {
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
        })*/
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.navHostFragment)
        navController.navigate(R.id.topImagesFragment)
    }
}