package com.test.conversion.data.mapper

import com.test.conversion.data.model.LatestConversionRatesResponse
import com.test.conversion.domain.model.ConversionResult

class LatestConversionRatesMapper {
    fun map(latestConversionRatesResponse: LatestConversionRatesResponse): List<ConversionResult> =
        latestConversionRatesResponse.rates.map {
            ConversionResult(
                latestConversionRatesResponse.baseCurrency,
                it.key,
                it.value
            )
        }
}