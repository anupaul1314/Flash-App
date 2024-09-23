package com.example.flash.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OtpScreen(
    otp: String,
    flashViewModal: FlashViewModal
) {
    OtpTextBox(otp = otp, flashViewModal = flashViewModal)
}

@Composable
fun OtpTextBox(
    otp: String,
    flashViewModal: FlashViewModal
) {

    BasicTextField(
        modifier = Modifier
            .fillMaxWidth(),
        singleLine = true,
        value = otp,
        onValueChange = {
            flashViewModal.setOtp(it)
        }
    ) {
        Row(
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(6) { index ->
                val number = when {
                    index >= otp.length -> ""
                    else -> otp[index].toString()
                }
                Column(
                    modifier = Modifier
                        .padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = number,
                        fontSize = 32.sp
                    )
                    Box(
                        modifier = Modifier
                            .width(40.dp)
                            .height(2.dp)
                            .background(Color.Gray)
                    ) {

                    }
                }
            }
        }
    }
}