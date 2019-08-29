package com.test.conversion.data.utils

import com.test.conversion.common.NetworkError
import com.test.conversion.common.ResultObject
import com.test.conversion.data.model.BaseNetworkResponse
import retrofit2.Call
import retrofit2.Response

internal fun <RESPONSE_TYPE : BaseNetworkResponse> Call<RESPONSE_TYPE>.runBlocking(): ResultObject<RESPONSE_TYPE> =
    try {
        val response = execute()
        if (response.isSuccessful && response.body()?.isSuccess == true) {
            response.body()?.let { ResultObject.Success(it) }
                ?: ResultObject.Error(response.toErrorCause())
        } else {
            ResultObject.Error(response.toErrorCause())
        }
    } catch (throwable: Throwable) {
        ResultObject.Error(throwable.toErrorCause())
    }

private fun Response<*>.toErrorCause() =
    NetworkError(this.code().toString()) // handle errors in some way

private fun Throwable.toErrorCause() = NetworkError("", this) // handle errors in some way