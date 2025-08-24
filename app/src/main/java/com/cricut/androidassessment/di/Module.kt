package com.cricut.androidassessment.di

import com.cricut.androidassessment.data.repository.QuizRepository
import com.cricut.androidassessment.data.repository.QuizRepositoryImpl
import com.cricut.androidassessment.data.source.local.QuizFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object QuizRepositoryModule {

    @Provides
    @Singleton
    fun providesQuizRepository(
        quizFactory: QuizFactory
    ): QuizRepository {
        return QuizRepositoryImpl(quizFactory)
    }

    @Provides
    @Singleton
    fun providesQuizFactory(): QuizFactory = QuizFactory()
}