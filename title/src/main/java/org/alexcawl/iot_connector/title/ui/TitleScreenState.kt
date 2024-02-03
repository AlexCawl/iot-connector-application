package org.alexcawl.iot_connector.title.ui

sealed interface TitleScreenState {
    data object Initial : TitleScreenState
}