package com.example.flash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flash.R

@Composable
fun LoginUi(
    flashViewModal: FlashViewModal
) {
    Column (
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .padding(top = 50.dp, bottom = 10.dp)
                .size(100.dp),
            painter = painterResource(id = R.drawable.ic_app_icon),
            contentDescription = "App Icon"
        )
        NumberScreen(flashViewModal = flashViewModal)
    }
}

@Preview
@Composable
private fun vjscksdc() {
    LoginUi(flashViewModal = FlashViewModal())
}