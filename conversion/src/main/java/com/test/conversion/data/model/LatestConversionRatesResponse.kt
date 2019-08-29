package com.test.conversion.data.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

class LatestConversionRatesResponse(
    @SerializedName("success")
    override val isSuccess: Boolean,

    @SerializedName("base")
    val baseCurrency: String,

    @SerializedName("rates")
    val rates: Map<String, BigDecimal>
) : BaseNetworkResponse()