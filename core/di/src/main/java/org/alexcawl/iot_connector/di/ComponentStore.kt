package org.alexcawl.iot_connector.di

interface ComponentStore<C, D> {
    var component: C

    fun inject(dependencies: D)
}