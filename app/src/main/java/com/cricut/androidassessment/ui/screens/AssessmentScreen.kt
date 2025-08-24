package com.cricut.androidassessment.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.cricut.androidassessment.R
import com.cricut.androidassessment.ui.AssessmentViewModel
import com.cricut.androidassessment.ui.theme.AndroidAssessmentTheme
import com.cricut.androidassessment.ui.utils.Cbutton
import com.cricut.androidassessment.ui.utils.Loader
import com.cricut.androidassessment.ui.utils.ProgressBar
import com.cricut.androidassessment.ui.utils.Quizzlet
import com.cricut.androidassessment.ui.utils.convertToAlpha

@Composable
fun QuestionHead(
    pageIndex: Int,
    size: Int
) {
    Column {
        Text(text = "Question", fontSize = 13.sp, fontWeight = FontWeight.Bold)
        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text("${pageIndex + 1}/${size}")
        }
        Spacer(modifier = Modifier.height(20.dp))
        ProgressBar(size = size, percentage = pageIndex)
    }
}

@Composable
fun AssessmentScreen(
    viewModel: AssessmentViewModel,
    modifier: Modifier = Modifier,
    onNavigate: () -> Unit
) {
    val bars = WindowInsets.systemBars.asPaddingValues()
    val quiz by viewModel.currentQuestion.collectAsStateWithLifecycle()
    val nextEnabled by viewModel.nextButtonEnabled.collectAsStateWithLifecycle()
    val prevEnabled by viewModel.prevButtonEnabled.collectAsStateWithLifecycle()
    val pageIndex by viewModel.currentIndex.collectAsStateWithLifecycle()
    val size by viewModel.size.collectAsStateWithLifecycle()
    val selectedOption by viewModel.selectedOption.collectAsStateWithLifecycle()
    val submitEnabled by viewModel.showSubmitButtonEnabled.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchAllQuizzes()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 15.dp, bottom = bars.calculateBottomPadding(), top = bars.calculateTopPadding(), end = 15.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.TopStart)
        ) {
            if(size.isNotEmpty()){
                QuestionHead(pageIndex, size.size)
            }
            Spacer(modifier = Modifier.height(20.dp))
            Column(
                modifier = Modifier.height(350.dp)
            ) {
                quiz?.let {
                    Text(
                        text = it.title,
                        modifier = Modifier
                            .background(colorResource(R.color.pink), RoundedCornerShape(10.dp))
                            .padding(10.dp)
                    )
                } ?: Loader()
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    quiz?.options?.let {
                        it.option?.forEachIndexed { index, op ->
                            Quizzlet(
                                index = convertToAlpha(index),
                                question = op.toString(),
                                selected = selectedOption[quiz!!.id] == op.toString()
                            ) {
                                viewModel.setSelectedOption(quiz!!.id, op.toString())
                            }
                        }
                    } ?: Loader()
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Cbutton(
                    text = "Previous",
                    modifier = Modifier.weight(1f),
                    enabled = prevEnabled
                ) {
                    viewModel.prevQuiz()
                }

                Cbutton(
                    text = "Next",
                    modifier = Modifier.weight(1f),
                    enabled = nextEnabled
                ) {
                    viewModel.nextQuiz()
                }
            }
        }
        if (pageIndex + 1 == size.size) {
            Cbutton(
                text = "Submit",
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomStart),
                enabled = submitEnabled!!
            ){
                onNavigate()
            }
        }
    }
}

@Composable
fun Navigation(navController: NavHostController){
    val viewModel: AssessmentViewModel = hiltViewModel()
    NavHost(
        navController = navController,
        startDestination = Routes.Home.name
    ){
        composable(Routes.Home.name){
            AssessmentScreen(viewModel) {
                navController.navigateToRoute(Routes.Overview.name)
            }
        }
        composable(Routes.Overview.name){
            OverviewScreen(viewModel) {
                navController.navigateToRoute(Routes.Home.name)
                viewModel.resetQuiz()
            }
        }
    }
}

fun NavController.navigateToRoute(route: String){
    this.navigate(route){
      popUpTo(graph.id) {
          inclusive = true
      }
    }
}

enum class Routes {
     Home, Overview
}


@Preview(showBackground = true)
@Composable
private fun PreviewAssessmentScreen() {
    AndroidAssessmentTheme {

    }
}
