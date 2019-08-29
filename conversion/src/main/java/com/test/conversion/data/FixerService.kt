package com.test.conversion.data

import com.test.conversion.data.model.ConversionRateResponse
import com.test.conversion.data.model.LatestConversionRatesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

private const val LATEST_CONVERSION_RATES_URL = "latest"
private const val CONVERSION_URL = "latest"

private const val QUERY_BASE_CURRENCY = "base"
private const val QUERY_CONVERT_FROM = "base"
private const val QUERY_CONVERT_TO = "symbols"

interface FixerService {

    @GET(LATEST_CONVERSION_RATES_URL)
    fun getLatestConversionRates(@Query(QUERY_BASE_CURRENCY) baseCurrency: String): Call<LatestConversionRatesResponse>

    @GET(CONVERSION_URL)
    fun getConversionRate(
        @Query(QUERY_CONVERT_FROM) fromCurrency: String,
        @Query(QUERY_CONVERT_TO) toCurrency: String
    ): Call<ConversionRateResponse> // Conversion request is not available in the free plan, so this one will be used instead
}