package com.cricut.androidassessment.data.util

sealed class Resource<out v>{
    object Loading: Resource<Nothing>()
    data class Success<T>(val data: T): Resource<T>()
    data class Error(val message: String): Resource<Nothing>()
}