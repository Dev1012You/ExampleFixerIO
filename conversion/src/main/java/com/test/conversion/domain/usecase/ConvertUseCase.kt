package com.test.conversion.domain.usecase

import com.test.conversion.common.ResultObject
import com.test.conversion.domain.model.ConversionRequest
import com.test.conversion.domain.model.ConversionResult
import com.test.conversion.domain.repository.ConversionRepository
import com.test.conversion.domain.repository.SettingsRepository
import kotlinx.coroutines.CoroutineScope

class ConvertUseCase(
    private val conversionRepository: ConversionRepository,
    private val settingsRepository: SettingsRepository
) : BackgroundExecuteUseCase<ConversionRequest, ResultObject<ConversionResult>>() {

    override suspend fun executeRequest(
        request: ConversionRequest,
        coroutineScope: CoroutineScope
    ): ResultObject<ConversionResult> {
        return conversionRepository.convert(
            baseCurrency = settingsRepository.baseCurrency,
            conversionCurrency = request.conversionCurrency,
            amount = request.amount
        )
    }
}