package com.example.flash.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun FlashApp(flashViewModal: FlashViewModal = viewModel()){
    StartScreen(flashViewModal = flashViewModal)
}




