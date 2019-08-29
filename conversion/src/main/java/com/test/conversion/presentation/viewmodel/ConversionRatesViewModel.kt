package com.test.conversion.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.conversion.common.ResultObject
import com.test.conversion.domain.model.ConversionResult
import com.test.conversion.domain.usecase.GetBaseConversionRatesUseCase
import com.test.conversion.domain.usecase.GetBaseCurrencyUseCase
import com.test.conversion.presentation.mapper.ConversionRateItemMapper
import com.test.conversion.presentation.model.ConversionRateItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ConversionRatesViewModel(
    private val getBaseConversionRatesUseCase: GetBaseConversionRatesUseCase,
    private val getBaseCurrencyUseCase: GetBaseCurrencyUseCase,
    private val conversionRateItemMapper: ConversionRateItemMapper
) : ViewModel() {

    private val _conversionRates = MutableLiveData<List<ConversionRateItem>>()
    val conversionRates: LiveData<List<ConversionRateItem>> = _conversionRates

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    private val _progress = MutableLiveData<Boolean>().apply { value = false }
    val progress: LiveData<Boolean> = _progress

    private val _baseCurrency = MutableLiveData<String>()
    val baseCurrency: LiveData<String> = _baseCurrency

    fun onResume() {
        _progress.value = true
        getBaseConversionRatesUseCase.execute(Unit) {
            _progress.value = false
            onConversionRatesUpdate(it)
        }

        getBaseCurrencyUseCase.execute(Unit) {
            _baseCurrency.value = it
        }
    }

    private fun onConversionRatesUpdate(output: ResultObject<List<ConversionResult>>) {
        output.handle { data ->
            viewModelScope.launch {
                val result = withContext(Dispatchers.IO) { data.map { conversionRateItemMapper.map(it) } }
                _conversionRates.value = result
            }
        }
    }

    private inline fun <T> ResultObject<T>.handle(onSuccess: (T) -> Unit) {
        when (this) {
            is ResultObject.Success -> onSuccess(this.value)
            is ResultObject.Error -> _error.postValue(errorCause.toString())
        }
    }

    override fun onCleared() {
        getBaseCurrencyUseCase.cancel()
        getBaseConversionRatesUseCase.cancel()
    }
}
