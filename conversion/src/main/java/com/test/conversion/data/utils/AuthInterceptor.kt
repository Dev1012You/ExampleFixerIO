package com.test.conversion.data.utils

import com.test.conversion.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

private const val QUERY_ACCESS_KEY = "access_key"

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder().addQueryParameter(QUERY_ACCESS_KEY, BuildConfig.api_key).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}