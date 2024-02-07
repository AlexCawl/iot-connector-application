package org.alexcawl.iot_connector.di

interface ComponentStore<C, D> {
    var dependencies: D

    val component: C
}