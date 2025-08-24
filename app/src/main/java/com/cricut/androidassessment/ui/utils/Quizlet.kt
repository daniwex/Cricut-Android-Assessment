package com.cricut.androidassessment.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.cricut.androidassessment.R

@Composable
fun Quizzlet(
    index: String,
    question: String,
    selected: Boolean,
    onClick: (Boolean) -> Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable{
            onClick(selected)
        }
            .then(if(selected) Modifier
                .background(colorResource(R.color.light_purple), RoundedCornerShape(10.dp))
                .border(1.dp, colorResource(R.color.border_purple), RoundedCornerShape(10.dp))
            else Modifier.background(colorResource(R.color.dark_purple),RoundedCornerShape(10.dp)))
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(index)
        Text(question, fontWeight = FontWeight.W300, fontSize = 13.sp)
    }
}