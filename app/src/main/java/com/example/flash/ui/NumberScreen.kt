package com.example.flash.ui

import android.app.Activity
import android.content.Context
import android.media.projection.MediaProjection
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit
import javax.security.auth.callback.Callback

@Composable
fun NumberScreen(
    flashViewModal: FlashViewModal,
    callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
) {
    val phoneNumber by flashViewModal.phoneNumber.collectAsState()
    val context = LocalContext.current
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = "LOGIN",
        textAlign = TextAlign.Center,
        fontSize = 25.sp,
        fontWeight = FontWeight.Bold
    )
    Spacer(modifier = Modifier.height(3.dp))
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
    
    Spacer(modifier = Modifier.height(5.dp))
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = phoneNumber,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        onValueChange = {
            flashViewModal.setPhoneNumber(it)
        },
        label = {
            Text(
                text = "Your Phone Number"
            )
        },
        singleLine = true
    )
    Spacer(modifier = Modifier.height(3.dp))
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            val options = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber("+91 ${phoneNumber}") // Phone number to verify
                .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(context as Activity) // Activity (for callback binding)
                .setCallbacks(callbacks) // OnVerificationStateChangedCallbacks
                .build()
            PhoneAuthProvider.verifyPhoneNumber(options)
        }
    ) {
        Text(text = "Send OTP")
    }
}
