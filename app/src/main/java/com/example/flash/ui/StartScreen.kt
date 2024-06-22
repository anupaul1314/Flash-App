package com.example.flash.ui
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import com.example.flash.R
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StartScreen(){

    Card(
        modifier = Modifier.clickable {

        }
    )
    {
        Column(
            modifier = Modifier
                .padding(start = 5.dp)
        ) {
            Text(
                text = "Hello World",
                fontSize = 24.sp,
                modifier = Modifier.width(150.dp)
            )
        }
        Image(
            painter = painterResource(id = R.drawable.ic_fruits) ,
            contentDescription ="Fresh Fruits",
            modifier = Modifier
                .size(150.dp)
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    StartScreen()
}