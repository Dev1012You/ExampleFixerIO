package com.test.conversion.common

sealed class ErrorCause(message: String = "", error: Throwable?) : Throwable(message, error)

class NetworkError(message: String = "", error: Throwable? = null) : ErrorCause(message, error)