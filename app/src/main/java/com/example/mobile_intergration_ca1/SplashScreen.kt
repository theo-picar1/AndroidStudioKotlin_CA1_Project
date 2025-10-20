package com.example.mobile_intergration_ca1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

class SplashScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                InitialSplash()
            }

            LaunchedEffect(Unit) {
                delay(2000)
                startActivity(Intent(this@SplashScreen, App1::class.java))
                finish()
            }
        }
    }
}

@Composable
fun InitialSplash() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(R.drawable.app1_icon),
                contentDescription = "App Icon",
                modifier = Modifier.size(100.dp)
            )
        }
    }
}
