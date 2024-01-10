package org.alexcawl.iot_connector.connection.domain

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class ConnectionsViewModel @Inject constructor() : ViewModel() {
    private val _state = MutableStateFlow("")
    val state = _state.asStateFlow()
}