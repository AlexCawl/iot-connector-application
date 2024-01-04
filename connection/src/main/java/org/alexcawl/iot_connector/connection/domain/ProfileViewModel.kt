package org.alexcawl.iot_connector.connection.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class ProfileViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow(5)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            while (true) {
                _state.emit(state.value + 1)
                delay(1000)
            }
        }
    }
}