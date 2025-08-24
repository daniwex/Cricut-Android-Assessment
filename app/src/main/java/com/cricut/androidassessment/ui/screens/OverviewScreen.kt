package com.cricut.androidassessment.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.cricut.androidassessment.ui.AssessmentViewModel
import com.cricut.androidassessment.ui.utils.Cbutton

@Composable
fun OverviewScreen(
    viewModel: AssessmentViewModel,
    onNavigate: () -> Unit
) {

    val score = viewModel.checkAnswers()
    val size = viewModel.size.collectAsStateWithLifecycle()
    val bars = WindowInsets.systemBars.asPaddingValues()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 15.dp, bottom = bars.calculateBottomPadding(), top = bars.calculateTopPadding(), end = 15.dp)
    ) {
        Text(
            "Quiz Result",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp)
        )

        Column(
           modifier = Modifier
               .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
           Text(
               text = viewModel.grade(),
               fontWeight = FontWeight.W500,
               fontSize = 95.sp
           )
            Text("Your Score")
            Text("$score/${size.value.size}", fontWeight = FontWeight.W500, fontSize = 27.sp)

        }
        Row(
            modifier = Modifier.fillMaxWidth()
                .align(Alignment.BottomStart),

        ) {
            Cbutton(
                text = "Restart Quiz",
                modifier = Modifier.fillMaxWidth()
            ) {
                onNavigate()
            }
        }
    }
}

