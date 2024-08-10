package com.example.flash.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
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

    fun toggleVisibility(){
        _isVisible.value = false;
    }
    init {
        viewModelScope.launch(Dispatchers.Default) {
            delay(3000)
            toggleVisibility()
        }
    }
}

