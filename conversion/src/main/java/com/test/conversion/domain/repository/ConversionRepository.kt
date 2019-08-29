package com.test.conversion.domain.repository

import com.test.conversion.common.ResultObject
import com.test.conversion.domain.model.ConversionResult
import java.math.BigDecimal

interface ConversionRepository {

    fun getConversionRates(baseCurrency: String): ResultObject<List<ConversionResult>>

    fun convert(baseCurrency: String, conversionCurrency: String, amount: BigDecimal): ResultObject<ConversionResult>
}