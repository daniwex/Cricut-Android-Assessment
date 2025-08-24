package com.cricut.androidassessment.data.model


data class Quiz(
    val id: String,
    val title: String,
    val options: Options,
    val answer: String
)



data class Options(
    val option: List<Any>?
)

