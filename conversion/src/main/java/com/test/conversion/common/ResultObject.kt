package com.test.conversion.common

sealed class ResultObject<out T> {
    data class Success<T>(val value: T) : ResultObject<T>()
    class Error(val errorCause: ErrorCause) : ResultObject<Nothing>()
}
