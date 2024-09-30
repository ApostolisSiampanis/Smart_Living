package com.aposiamp.smartliving.domain.utils

sealed class Result<out T> {
    data class Success<out T>(val result: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()
    data object Loading : Result<Nothing>()
}