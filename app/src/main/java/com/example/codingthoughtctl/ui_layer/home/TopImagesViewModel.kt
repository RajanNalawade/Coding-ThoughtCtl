package com.example.codingthoughtctl.ui_layer.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingthoughtctl.domain_layer.GetTopWeeklyImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopImagesViewModel @Inject constructor(
    private val topWeekUseCase: GetTopWeeklyImagesUseCase
) : ViewModel() {
    internal val topImagesResult get() = topWeekUseCase.mTopWeeklyImages

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getData()
        }
    }

    private suspend fun getData() {
        topWeekUseCase(
            "top",
            "top",
            "week"
        )
    }
}