package org.disf.app.mobileuptrainee.presentation.ext

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun CoroutineScope.safeLaunch(
    block: suspend CoroutineScope.() -> Unit,
    onError: (Throwable) -> Unit
) {
    val errorHandler = CoroutineExceptionHandler { _, throwable ->
        onError.invoke(throwable)
    }
    launch(errorHandler) {
        block.invoke(this)
    }
}