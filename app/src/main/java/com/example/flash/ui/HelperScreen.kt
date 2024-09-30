package com.example.flash.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun YourCartWithBadge() {
    Column(
        modifier = Modifier
            .clickable {
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Box(
            modifier = Modifier
        ) {
            Icon(
                imageVector = Icons.Outlined.ShoppingCart,
                contentDescription = "Cart"
            )
                Card(
                    modifier = Modifier
                        .align(Alignment.TopEnd),
                    colors = CardDefaults.cardColors(
                        Color.Red
                    )
                ) {
                    Text(
                        modifier = Modifier
                            .padding(
                                horizontal = 1.dp
                            ),
                        text = "0",
                        fontSize = 10.sp,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold
                    )
                }
        }
        Text(
            text = "Cart",
            fontSize = 10.sp
        )
    }
}

@Preview
@Composable
private fun bjsbcsa() {
    YourCartWithBadge()
}