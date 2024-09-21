package com.example.flash.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier

@Composable
fun OtpScreen(
    flashViewModal: FlashViewModal,
    otp: String
) {
    OtpTextBox(otp = otp, flashViewModal = flashViewModal)
}

@Composable
fun OtpTextBox(
    flashViewModal: FlashViewModal,
    otp: String
) {

    BasicTextField(
        modifier = Modifier
            .fillMaxWidth(),
        singleLine = true,
        value = otp,
        onValueChange = {
            flashViewModal.setOtp(it)
        }
    )
}