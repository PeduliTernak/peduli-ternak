package com.example.peduliternak.view

sealed class Result<out T : Any> {
    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val message: String?, val cause: Exception? = null) : Result<Nothing>()
    object Loading : Result<Nothing>()
}