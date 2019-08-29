package com.test.conversion.data

import com.test.conversion.common.ResultObject
import com.test.conversion.common.mapIfSuccess
import com.test.conversion.data.mapper.ConversionResultMapper
import com.test.conversion.data.mapper.LatestConversionRatesMapper
import com.test.conversion.data.utils.runBlocking
import com.test.conversion.domain.model.ConversionResult
import java.math.BigDecimal

class ConversionDataSource(
    private val fixerService: FixerService,
    private val conversionResultMapper: ConversionResultMapper,
    private val latestRatesMapper: LatestConversionRatesMapper
) {

    fun getConversionRates(baseCurrency: String): ResultObject<List<ConversionResult>> =
        fixerService.getLatestConversionRates(baseCurrency)
            .runBlocking()
            .mapIfSuccess {
                latestRatesMapper.map(it)
            }

    fun convert(
        currencyFrom: String,
        currencyTo: String,
        amount: BigDecimal
    ): ResultObject<ConversionResult> =
        fixerService.getConversionRate(currencyFrom, currencyTo)
            .runBlocking()
            .mapIfSuccess {
                conversionResultMapper.map(it, currencyTo, amount)
            }
}