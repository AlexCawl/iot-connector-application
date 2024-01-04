package org.alexcawl.iot_connector.connection.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.alexcawl.iot_connector.network.IDataSource
import javax.inject.Inject

class ConnectionsViewModel @Inject constructor(
    private val dataSource: IDataSource
) : ViewModel() {
    private val _state = MutableStateFlow("")
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            dataSource.getData().collect {
                _state.emit(it)
            }
        }
    }
}