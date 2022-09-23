package org.disf.app.mobileuptrainee.presentation.base

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

abstract class BaseViewModel <S> {

    protected abstract val initialState: S

    private val _stateFlow: MutableStateFlow<S> = MutableStateFlow(initialState)
    protected val stateFlow: StateFlow<S> = _stateFlow

    protected fun updateState(transform: S.() -> S) {
        _stateFlow.value = _stateFlow.value.transform()
    }
}