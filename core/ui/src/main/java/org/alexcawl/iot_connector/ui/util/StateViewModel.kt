package org.alexcawl.iot_connector.ui.util

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow

abstract class StateViewModel <S, A> : ViewModel() {
    abstract val state: StateFlow<S>

    abstract fun handle(action: A)
}