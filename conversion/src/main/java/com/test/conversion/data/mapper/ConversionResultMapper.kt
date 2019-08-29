package com.test.conversion.data.mapper

import com.test.conversion.data.model.ConversionRateResponse
import com.test.conversion.domain.model.ConversionResult
import java.math.BigDecimal

class ConversionResultMapper {
    fun map(
        conversionResponse: ConversionRateResponse,
        currencyTo: String,
        inputValue: BigDecimal
    ): ConversionResult =
        ConversionResult(
            currencyFrom = conversionResponse.currencyFrom,
            currencyTo = currencyTo,
            convertedValue = conversionResponse.convert(currencyTo, inputValue)
        )

    private fun ConversionRateResponse.convert(
        currencyTo: String,
        inputValue: BigDecimal
    ): BigDecimal {
        val rate = getRate(currencyTo) ?: BigDecimal.ZERO
        return inputValue * rate
    }
}