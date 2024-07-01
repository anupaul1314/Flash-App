package com.example.flash.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel

@Composable
fun FlashApp(flashViewModal: ViewModel = vi()){
    StartScreen(flashViewModal = flashViewModal)
}




