package com.test.conversion.ui.utils

import android.content.Context

class FlagUtil(context: Context) {
    private val resources = context.resources
    private val packgageName = context.packageName

    fun getFlagResource(currencyName: String): Int = if (currencyName.isEmpty()) {
        0
    } else {
        val resName = "flag_${currencyName.toLowerCase()}"
        resources.getIdentifier(
            resName, "drawable",
            packgageName
        )
    }
}