package com.example.codingthoughtctl.domain_layer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.codingthoughtctl.data_layer.remote.models.ImgurResponce
import com.example.codingthoughtctl.data_layer.remote.repository.ImgurRepository
import com.example.codingthoughtctl.utilities.NetworkResult
import javax.inject.Inject

class GetTopSearchQueryUseCase @Inject constructor(private val imgurRepository: ImgurRepository) {

    private val topSearchImages = MutableLiveData<NetworkResult<ImgurResponce>>()
    val mTopSearchImages: LiveData<NetworkResult<ImgurResponce>> =
        topSearchImages

    suspend operator fun invoke(
        sort: String,
        query: String?
    ) {
        try {
            topSearchImages.postValue(NetworkResult.Loading())

            val result = imgurRepository.getTopSearchQuery(sort, query)
            if (result.isSuccessful) {
                topSearchImages.postValue(NetworkResult.Success(result.body()!!))
            } else if (result.errorBody() != null) {
                topSearchImages.postValue(NetworkResult.Error(errorMessage = result.message()))
            } else {
                topSearchImages.postValue(NetworkResult.Error(errorMessage = "Something went wrong.."))
            }
        } catch (
            e: Exception
        ) {
            topSearchImages.postValue(NetworkResult.Error(errorMessage = e.message))
        }
    }

}