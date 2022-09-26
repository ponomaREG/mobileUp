package org.disf.app.mobileuptrainee.presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Абстрактный класс вьюмодели
 * @param S - Класс стейта View
 * @see State
 */
abstract class BaseViewModel<S : State> : ViewModel() {

    protected abstract val initialState: S

    private val _stateFlow: MutableStateFlow<S> = MutableStateFlow(initialState)
    val stateFlow: StateFlow<S> = _stateFlow

    private val _eventFlow: MutableSharedFlow<Event> = MutableSharedFlow(
        replay = 0
    )
    val eventFlow: SharedFlow<Event> = _eventFlow

    val state: S
        get() = stateFlow.value

    protected fun updateState(transform: S.() -> S) {
        _stateFlow.value = _stateFlow.value.transform()
    }

    protected fun submitEvent(event: Event) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }
}

/**
 * Интерфейс состояния View
 */
interface State

/**
 * Интерфейс SingleLiveEvent'a
 * @see org.disf.app.mobileuptrainee.presentation.model.event
 */
interface Event