package com.example.flash.ui

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FlashViewModal {
    private val _uistate = MutableStateFlow(FlashUiState())
    val uiState:StateFlow<FlashUiState> = _uistate.asStateFlow()
}