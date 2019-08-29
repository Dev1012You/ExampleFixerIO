package com.test.conversion.common

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

private const val DIGITS_PATTERN = "#0.00"
private const val DECIMAL_PLACES_AMOUNT = 2

class BigDecimalFormatter {
    private val twoDecimalPlacesFormatter by lazy {
        DecimalFormat(DIGITS_PATTERN).apply {
            maximumFractionDigits = DECIMAL_PLACES_AMOUNT
            minimumFractionDigits = DECIMAL_PLACES_AMOUNT
        }
    }

    fun format(value: BigDecimal): String {
        val scaledValue = value.setScale(DECIMAL_PLACES_AMOUNT, RoundingMode.FLOOR)
        return twoDecimalPlacesFormatter.format(scaledValue)
    }
}