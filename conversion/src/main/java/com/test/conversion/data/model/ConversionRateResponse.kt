package com.test.conversion.data.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class ConversionRateResponse(
    @SerializedName("success")
    override val isSuccess: Boolean,

    @SerializedName("base")
    val currencyFrom: String,

    @SerializedName("rates")
    val rates: Map<String, BigDecimal>
) : BaseNetworkResponse() {
    fun getRate(currencyName: String): BigDecimal? = rates[currencyName]
}