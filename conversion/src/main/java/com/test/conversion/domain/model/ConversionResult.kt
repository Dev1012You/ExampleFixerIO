package com.test.conversion.domain.model

import java.math.BigDecimal

data class ConversionResult(val currencyFrom: String, val currencyTo: String, val convertedValue: BigDecimal)