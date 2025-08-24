package com.cricut.androidassessment.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.cricut.androidassessment.R

@Composable
fun ProgressBar(
    size: Int,
    percentage: Int
) {
    val width = (percentage + 1).toFloat() / size
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(4.dp)
            .background(
                colorResource(R.color.light_purple),
                RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
            )
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(width.dp.value)
                .background(colorResource(R.color.border_purple))
        ) {}
    }
}