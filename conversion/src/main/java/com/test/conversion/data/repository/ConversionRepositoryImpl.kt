package com.test.conversion.data.repository

import com.test.conversion.common.ResultObject
import com.test.conversion.data.ConversionDataSource
import com.test.conversion.domain.model.ConversionResult
import com.test.conversion.domain.repository.ConversionRepository
import java.math.BigDecimal

class ConversionRepositoryImpl(private val conversionDataSource: ConversionDataSource) :
    ConversionRepository {

    override fun getConversionRates(baseCurrency: String): ResultObject<List<ConversionResult>> =
        conversionDataSource.getConversionRates(baseCurrency)

    override fun convert(
        baseCurrency: String,
        conversionCurrency: String,
        amount: BigDecimal
    ): ResultObject<ConversionResult> =
        conversionDataSource.convert(baseCurrency, conversionCurrency, amount)
}