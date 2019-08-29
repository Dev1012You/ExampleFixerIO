package com.test.conversion.domain.model

import java.math.BigDecimal

data class ConversionRequest(
    val conversionCurrency: String,
    val amount: BigDecimal
)