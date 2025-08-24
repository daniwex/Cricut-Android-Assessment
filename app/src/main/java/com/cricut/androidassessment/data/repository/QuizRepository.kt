package com.cricut.androidassessment.data.repository

import com.cricut.androidassessment.data.model.Quiz
import com.cricut.androidassessment.data.util.Resource
import kotlinx.coroutines.flow.Flow

interface QuizRepository {
    fun fetchAllQuiz(): Flow<Resource<List<Quiz>>>
    fun fetchQuiz(id: String): Flow<Resource<Quiz>>
}