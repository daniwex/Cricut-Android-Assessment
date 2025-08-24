package com.cricut.androidassessment.ui.utils

fun convertToAlpha(id: Int) = (65 + id).toChar().lowercase()


fun compileGrade(score: Int, total: Int): Char {
    val diff = total - score
    return when {
        diff <= 2 -> 'A'
        diff > 2 && diff <= 4 && score > 0 -> 'B'
        diff > 4 && diff <= 7 && score > 0 -> 'C'
        else -> 'F'
    }
}