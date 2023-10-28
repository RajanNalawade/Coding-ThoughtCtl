package com.example.codingthoughtctl.domain_layer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.codingthoughtctl.data_layer.remote.models.ImgurResponce
import com.example.codingthoughtctl.data_layer.remote.repository.ImgurRepository
import com.example.codingthoughtctl.utilities.NetworkResult
import javax.inject.Inject

class GetTopWeeklyImagesUseCase @Inject constructor(private val imgurRepository: ImgurRepository) {

    private val topWeeklyImages = MutableLiveData<NetworkResult<ImgurResponce>>()
    val mTopWeeklyImages: LiveData<NetworkResult<ImgurResponce>> =
        topWeeklyImages

    suspend operator fun invoke(
        section: String,
        sort: String,
        window: String
    ) {
        try {
            topWeeklyImages.postValue(NetworkResult.Loading())

            val result = imgurRepository.getTopWeeklyImages(section, sort, window)
            if (result.isSuccessful) {
                topWeeklyImages.postValue(NetworkResult.Success(result.body()!!))
            } else if (result.errorBody() != null) {
                topWeeklyImages.postValue(NetworkResult.Error(errorMessage = result.message()))
            } else {
                topWeeklyImages.postValue(NetworkResult.Error(errorMessage = "Something went wrong.."))
            }
        } catch (
            e: Exception
        ) {
            topWeeklyImages.postValue(NetworkResult.Error(errorMessage = e.message))
        }
    }

}