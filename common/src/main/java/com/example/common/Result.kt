package com.example.common

import java.lang.Exception

sealed class Result<out T> {
    data class Success<out T>(val data: T): Result<T>()

    data class Failure(val exception: Exception): Result<Nothing>()

    data object Loading: Result<Nothing>()
}