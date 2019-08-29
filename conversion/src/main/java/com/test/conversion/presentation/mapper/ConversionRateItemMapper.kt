package com.test.conversion.presentation.mapper

import com.test.conversion.common.BigDecimalFormatter
import com.test.conversion.domain.model.ConversionResult
import com.test.conversion.presentation.model.ConversionRateItem

class ConversionRateItemMapper(private val bigDecimalFormatter: BigDecimalFormatter) {
    fun map(conversionResult: ConversionResult): ConversionRateItem = ConversionRateItem(
        conversionResult.currencyTo,
        bigDecimalFormatter.format(conversionResult.convertedValue)
    )
}