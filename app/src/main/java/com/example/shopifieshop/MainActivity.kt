package com.example.shopifieshop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.shopifieshop.ui.splash.SplashScreen
import com.example.studentcrud.ui.screens.ProductScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.time.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var showSplash by remember {
                mutableStateOf(true)
            }

            LaunchedEffect(Unit) {

                delay(3000)

                showSplash = false
            }

            if (showSplash) {

                SplashScreen()

            } else {

                ProductScreen()
            }
        }
    }
}
