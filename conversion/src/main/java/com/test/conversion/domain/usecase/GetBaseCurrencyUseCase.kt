package com.test.conversion.domain.usecase

import com.test.conversion.domain.repository.SettingsRepository

class GetBaseCurrencyUseCase(private val settingsRepository: SettingsRepository) : BaseUseCase<Unit, String>() {
    override fun execute(value: Unit, callback: (String) -> Unit) {
        val result = settingsRepository.baseCurrency
        callback(result)
    }
}