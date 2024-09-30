package com.example.flash.ui

import android.app.Activity
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
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
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

@Composable
fun OtpScreen(
    otp: String,
    flashViewModal: FlashViewModal
) {
    val context = LocalContext.current
    val verificationId by flashViewModal.verificationId.collectAsState()
    OtpTextBox(otp = otp, flashViewModal = flashViewModal)

    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            if (otp.isEmpty()) {
                Toast.makeText(context,"Please Enter OTP",Toast.LENGTH_SHORT).show()
            } else {
                val credential = PhoneAuthProvider.getCredential(verificationId!!, otp)
                signInWithPhoneAuthCredential(credential = credential,context = context,flashViewModal = flashViewModal)
            }
        }
    ) {
        Text(text = "Verify OTP")
    }
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

private fun signInWithPhoneAuthCredential(
    credential: PhoneAuthCredential,
    context: Context,
    flashViewModal: FlashViewModal
) {
    auth.signInWithCredential(credential)
        .addOnCompleteListener(context as Activity) { task ->
            if (task.isSuccessful) {
                Toast.makeText(context,"Verification Successful",Toast.LENGTH_SHORT).show()
                val user = task.result?.user
                if (user != null) {
                    flashViewModal.setUser(user)
                }
            } else {
                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    Toast.makeText(context,"The OTP you have entered is invalid. Please try again.",Toast.LENGTH_LONG).show()
                }
            }
        }
}