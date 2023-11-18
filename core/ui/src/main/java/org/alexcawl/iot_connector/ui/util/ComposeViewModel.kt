package org.alexcawl.iot_connector.ui.util

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
inline fun <reified F : ViewModelProvider.Factory, reified VM : ViewModel> composeViewModel(
    modelClass: Class<VM>,
    crossinline viewModelInstanceCreator: () -> F
): VM = viewModel(
    modelClass = modelClass,
    key = "$modelClass",
    factory = viewModelInstanceCreator()
)