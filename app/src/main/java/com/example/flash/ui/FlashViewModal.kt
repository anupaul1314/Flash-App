package com.example.flash.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flash.network.FlashApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FlashViewModal: ViewModel() {
    private val _uistate = MutableStateFlow(FlashUiState())
    val uiState:StateFlow<FlashUiState> = _uistate.asStateFlow()

    val _isVisible = MutableStateFlow<Boolean>(true)
    val isVisible = _isVisible

    var itemUiState:ItemUiState by mutableStateOf(ItemUiState.Loading)
        private set

    lateinit var internetJob:Job
    lateinit var screenJob:Job

    sealed interface ItemUiState {
        data class Success(val items:String): ItemUiState
        object Loading: ItemUiState
        object Error: ItemUiState
    }

    fun updateClickText(updatedText:String){
        _uistate.update {
            it.copy(
                clickStatus = updatedText
            )
        }
    }

    fun updateSelectedCategory(updatedCategory: Int){
        _uistate.update {
            it.copy(
                selectedCategory = updatedCategory
            )
        }
    }

    fun toggleVisibility() {
        _isVisible.value = false
    }

    fun getFlashItems() {
        internetJob = viewModelScope.launch {
            try {
                val listResult = FlashApi.retrofitService.getItems()
                itemUiState = ItemUiState.Success(listResult)
            }
            catch (exception:Exception) {
                itemUiState = ItemUiState.Error
                toggleVisibility()
                screenJob.cancel()
            }
        }
    }
    init {
        screenJob = viewModelScope.launch(Dispatchers.Default) {
            delay(3000)
            toggleVisibility()
        }
        getFlashItems()
    }
}

