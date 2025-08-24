package com.cricut.androidassessment.data.repository

import com.cricut.androidassessment.data.model.Quiz
import com.cricut.androidassessment.data.source.local.QuizFactory
import com.cricut.androidassessment.data.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuizRepositoryImpl @Inject constructor(
    val quizFactory: QuizFactory
): QuizRepository {
    override fun fetchAllQuiz(): Flow<Resource<List<Quiz>>>  = flow {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(quizFactory.fetchQuizzes()))
        } catch (e: Exception) {
            emit(Resource.Error(message = "something went wrong, please try again later"))
        }
    }
    override fun fetchQuiz(id: String): Flow<Resource<Quiz>> = flow {
        emit(Resource.Loading)
        try {
            emit(Resource.Success(quizFactory.fetchQuiz(id)!!))
        } catch (e: Exception) {
            emit(Resource.Error(message = "something went wrong, please try again later"))
        }
    }

}