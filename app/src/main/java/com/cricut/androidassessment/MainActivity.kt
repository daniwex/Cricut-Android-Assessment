package com.cricut.androidassessment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.cricut.androidassessment.ui.screens.Navigation
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidAssessmentTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) {  _ ->
                    Navigation(navController)
                }
            }
        }
    }
}
