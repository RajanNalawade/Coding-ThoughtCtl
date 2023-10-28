package com.example.codingthoughtctl.ui_layer.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingthoughtctl.domain_layer.GetTopSearchQueryUseCase
import com.example.codingthoughtctl.domain_layer.GetTopWeeklyImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val topWeekUseCase: GetTopWeeklyImagesUseCase,
    private val topSearchQueryUseCase: GetTopSearchQueryUseCase
) :
    ViewModel() {
    internal val topImagesResult get() = topWeekUseCase.mTopWeeklyImages
    internal val topSearchResult get() = topSearchQueryUseCase.mTopSearchImages

    init {
        viewModelScope.launch {
            getData()
        }
    }

    private suspend fun getData() = withContext(Dispatchers.IO) {
        topWeekUseCase(
            "top",
            "top",
            "week"
        )

        topSearchQueryUseCase("top", "mobiles")
    }

}