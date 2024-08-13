package com.example.flash.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.flash.R

@Composable
fun OfferScren(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(23, 105, 201, 255)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
            Image(
                modifier = Modifier
                    .padding(start = 20.dp, end = 10.dp)
                    .fillMaxSize(),
                painter = painterResource(id = R.drawable.ic_offer),
                contentDescription = "",
            )

    }
}

@Preview
@Composable
private fun vdbbvkjs() {
    OfferScren()
}