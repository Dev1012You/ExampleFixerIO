package com.test.conversion.data.repository

import com.test.conversion.domain.repository.SettingsRepository

private const val BASE_CURRENCY = "EUR"

class SettingsRepositoryImpl : SettingsRepository {
    override val baseCurrency: String
        get() = BASE_CURRENCY
}