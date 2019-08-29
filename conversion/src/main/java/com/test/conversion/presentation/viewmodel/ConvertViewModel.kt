package com.test.conversion.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.test.conversion.common.BigDecimalFormatter
import com.test.conversion.common.ResultObject
import com.test.conversion.domain.model.ConversionRequest
import com.test.conversion.domain.usecase.ConvertUseCase
import com.test.conversion.domain.usecase.GetBaseCurrencyUseCase
import java.math.BigDecimal

class ConvertViewModel(
    private val convertUseCase: ConvertUseCase,
    private val getBaseCurrencyUseCase: GetBaseCurrencyUseCase,
    private val bigDecimalFormatter: BigDecimalFormatter
) : ViewModel() {
    private lateinit var conversionCurrency: String

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    val baseCurrencyName: LiveData<String> = MutableLiveData<String>().apply {
        getBaseCurrencyUseCase.execute(Unit) { postValue(it) }
    }

    private val _convertedValue = MutableLiveData<String>()
    val convertedValue: LiveData<String> = _convertedValue

    private val _conversionCurrency = MutableLiveData<String>()
    val conversionCurrencyName: LiveData<String> = _conversionCurrency

    fun onResume(conversionCurrency: String, baseCurrencyValue: String) {
        this.conversionCurrency = conversionCurrency
        _conversionCurrency.value = conversionCurrency
        onInputValueChange(baseCurrencyValue)
    }

    fun onInputValueChange(newValue: String) {
        val value = newValue.toBigDecimalOrNull() ?: BigDecimal.ONE
        if (value == BigDecimal.ZERO) {
            _convertedValue.value = bigDecimalFormatter.format(BigDecimal.ZERO)
            return
        }

        // todo add some debounce
        convertUseCase.execute(ConversionRequest(conversionCurrency, value)) {
            it.handle { result ->
                _convertedValue.value = bigDecimalFormatter.format(result.convertedValue)
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
        super.onCleared()
        convertUseCase.cancel()
    }
}