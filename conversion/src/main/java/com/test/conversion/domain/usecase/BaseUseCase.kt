package com.test.conversion.domain.usecase

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

abstract class BaseUseCase<IN_TYPE, OUT_TYPE> {
    private val job = Job()
    protected val uiScope = CoroutineScope(Dispatchers.Main + job)

    abstract fun execute(value: IN_TYPE, callback: (OUT_TYPE) -> Unit)

    open fun cancel() {
        job.cancel()
    }
}