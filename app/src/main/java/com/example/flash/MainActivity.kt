package com.example.flash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.flash.ui.FlashApp
import com.example.flash.ui.FlashViewModal
import com.example.flash.ui.StartScreen
import com.example.flash.ui.theme.FlashTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlashApp()
        }
    }
}

@Composable
fun Greeting() {

}

