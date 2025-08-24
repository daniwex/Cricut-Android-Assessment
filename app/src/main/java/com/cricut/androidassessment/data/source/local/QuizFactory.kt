package com.cricut.androidassessment.data.source.local

import com.cricut.androidassessment.data.model.Options
import com.cricut.androidassessment.data.model.Quiz

class QuizFactory {

    private val quizList = listOf(
        Quiz(
            id = "1",
            title = "Which of the following is the preferred programming language for Android app development?",
            options = Options(
                option = listOf(
                    "Swift", "Kotlin", "Objective-C", "Java"
                )
            ),
            answer = "Kotlin"
        ),
        Quiz(
            id = "2",
            title = "Android Studio is the official IDE for Android development",
            options = Options(
                option = listOf(true, false)
            ),
            answer = "true"
        ),
        Quiz(
            id = "3",
            title = "Kotlin is the official language for Android development",
            options = Options(
                option = listOf(true, false)
            ),
            answer = "true"
        ),
        Quiz(
            id = "4",
            title = "Who is the father of Linux operating system",
            options = Options(
                option = listOf(
                    "Linus Torvalds", "Bill gates", "Michael Jackson", "Elon musk"
                )
            ),
            answer = "Linus Torvalds"
        ),
        Quiz(
            id = "5",
            title = "How is data saved during orientation changes?",
            options = Options(
                option = listOf(
                    "Activity", "View Model", "Fragment + Activity", "Data Stream"
                )
            ),
            answer = "View Model"
        ),
        Quiz(
            id = "6",
            title = "Dependency Injection Libraries include one of the following",
            options = Options(
                option = listOf(
                    "Jet Pack compose", "Material 3", "Live Data", "Hilt"
                )
            ),
            answer = "Hilt"
        ),
        Quiz(
            id = "7",
            title = "Types of coroutine dispatches includes all but one of the following",
            options = Options(
                option = listOf(
                    "Dispatchers.IO", "Dispatchers.Context", "Dispatchers.Main", "Dispatchers.Default"
                )
            ),
            answer = "Dispatchers.Context"
        ),
        Quiz(
            id = "8",
            title = "Which of the following is part of the Android Activity Lifecycle",
            options = Options(
                option = listOf(
                    "OnCreate", "OnBegin", "OnViewCreated", "OnAttach"
                )
            ),
            answer = "OnCreate"
        ),
        Quiz(
            id = "9",
            title = "Which of the following is a valid dialog box supported on Android",
            options = Options(
                option = listOf(
                    "DefaultDialog", "YearsPickerDialog", "TimePickerDialog", "WarningDialog"
                )
            ),
            answer = "TimePickerDialog"
        ),
        Quiz(
            id = "10",
            title = "Which of the following is a type of context in Android",
            options = Options(
                option = listOf(
                    "Activity Context", "Fragment Context", "Broadcast receivers", "Services"
                )
            ),
            answer = "Activity Context"
        ),
    )


    fun fetchQuizzes(): List<Quiz> = quizList

    fun fetchQuiz(id: String) = quizList.find { it.id == id }
}