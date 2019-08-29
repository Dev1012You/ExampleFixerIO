package com.test.conversion.domain.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

abstract class BackgroundExecuteUseCase<REQUEST, RESPONSE> : BaseUseCase<REQUEST, RESPONSE>() {

    override fun execute(value: REQUEST, callback: (RESPONSE) -> Unit) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                executeRequest(value, this)
            }.let(callback)
        }
    }

    abstract suspend fun executeRequest(
        request: REQUEST,
        coroutineScope: CoroutineScope
    ): RESPONSE
}