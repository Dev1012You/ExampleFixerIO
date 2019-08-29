package com.test.conversion.ui.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.conversion.di.Dependencies
import com.test.conversion.presentation.viewmodel.ConversionRatesViewModel
import com.test.conversion.presentation.viewmodel.ConvertViewModel

class CustomViewModelFactory(private val dependencies: Dependencies) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when {
        modelClass.isAssignableFrom(ConversionRatesViewModel::class.java) -> ConversionRatesViewModel(
            dependencies.baseConversionRatesUseCase(),
            dependencies.getBaseCurrencyUseCase(),
            dependencies.conversionRateItemMapper()
        ) as T
        modelClass.isAssignableFrom(ConvertViewModel::class.java) -> ConvertViewModel(
            Dependencies.conversionUseCase(),
            Dependencies.getBaseCurrencyUseCase(),
            Dependencies.bigDecimalFormatter
        ) as T
        else -> throw IllegalArgumentException("Unknown ViewModel class")
    }
}