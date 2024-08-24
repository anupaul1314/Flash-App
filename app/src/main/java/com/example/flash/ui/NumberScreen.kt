package com.example.flash.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun NumberScreen(
    flashViewModal: FlashViewModal
) {
    val phoneNumber by flashViewModal.phoneNumber.collectAsState()
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = "LOGIN",
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    )
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = "Enter your phone number to proceed",
        fontSize = 20.sp
    )
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = "This phone number will be used for purpose of all communication. You shall receive an SMS with a code for verification",
        fontSize = 12.sp,
        color = Color(105,103,100)
    )
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = phoneNumber,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        onValueChange = {}
    )
}

@Preview
@Composable
private fun NumberScreenPreview() {
    Column {
        NumberScreen(flashViewModal = FlashViewModal())
    }
}