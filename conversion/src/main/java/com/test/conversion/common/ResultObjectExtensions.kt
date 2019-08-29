package com.test.conversion.common

inline fun <T, P> ResultObject<T>.mapIfSuccess(transformation: (T) -> P): ResultObject<P> =
    when (this) {
        is ResultObject.Success -> ResultObject.Success(transformation(this.value))
        is ResultObject.Error -> this
    }