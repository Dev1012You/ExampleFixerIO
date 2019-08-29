package com.test.conversion.domain.usecase

import com.test.conversion.common.ResultObject
import com.test.conversion.domain.model.ConversionResult
import com.test.conversion.domain.repository.ConversionRepository
import com.test.conversion.domain.repository.SettingsRepository
import kotlinx.coroutines.CoroutineScope

class GetBaseConversionRatesUseCase(
    private val repository: ConversionRepository,
    private val settingsRepository: SettingsRepository
) : BackgroundExecuteUseCase<Unit, ResultObject<List<ConversionResult>>>() {

    override suspend fun executeRequest(
        request: Unit,
        coroutineScope: CoroutineScope
    ): ResultObject<List<ConversionResult>> = repository.getConversionRates(settingsRepository.baseCurrency)
}