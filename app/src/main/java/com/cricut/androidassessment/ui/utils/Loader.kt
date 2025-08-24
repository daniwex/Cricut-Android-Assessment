package com.cricut.androidassessment.ui.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun Loader() {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}