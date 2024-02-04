package org.alexcawl.iot_connector.client.di

import androidx.lifecycle.ViewModelProvider

interface ClientDependencies {
    val viewModelFactory: ViewModelProvider.Factory
}