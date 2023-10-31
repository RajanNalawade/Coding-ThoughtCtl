package com.example.codingthoughtctl.ui_layer.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codingthoughtctl.data_layer.remote.models.Data
import com.example.codingthoughtctl.domain_layer.GetTopSearchQueryUseCase
import com.example.codingthoughtctl.domain_layer.GetTopWeeklyImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopImagesViewModel @Inject constructor(
    private val topWeekUseCase: GetTopWeeklyImagesUseCase,
    private val topSearchQueryUseCase: GetTopSearchQueryUseCase
) : ViewModel() {
    internal val topImagesResult get() = topWeekUseCase.mTopWeeklyImages
    internal val topSearchResult get() = topSearchQueryUseCase.mTopSearchImages

    val isSearchOpen = MutableLiveData(false)
    val query = MutableLiveData<String>()

    private val uiStateCloseSearchView = MutableLiveData(false)
    val mUiStateCloseSearchView: LiveData<Boolean> get() = uiStateCloseSearchView

    private val uiStateSearchView = MutableLiveData<SearchViewUiState>(SearchViewUiState.Hidden)
    val mUiStateSearchView: LiveData<SearchViewUiState> get() = uiStateSearchView

    private val uiStateSearchResult = MutableLiveData<List<Data>?>(null)
    val mUiStateSearchResult: LiveData<List<Data>?> get() = uiStateSearchResult


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

        if (!query.value.isNullOrEmpty()) {
            topSearchQueryUseCase("top", query = query.value)
        }
    }

    fun searchViewVisible() {
        viewModelScope.launch {
            uiStateSearchView.postValue(SearchViewUiState.Visible)
        }
    }

    fun searchViewHidden() {
        viewModelScope.launch {
            uiStateSearchView.postValue(SearchViewUiState.Hidden)
            isSearchOpen.postValue(false)
        }
    }

    fun closeSearch() {
        viewModelScope.launch {
            uiStateCloseSearchView.postValue(true)
        }
    }

    fun closeSearchDone() {
        viewModelScope.launch {
            uiStateCloseSearchView.postValue(false)
        }
    }
}