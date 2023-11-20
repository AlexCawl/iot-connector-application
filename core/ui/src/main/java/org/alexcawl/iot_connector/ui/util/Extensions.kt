package org.alexcawl.iot_connector.ui.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun Long.toDateFormat(): String = run {
    val date = Date(this)
    val formatter = SimpleDateFormat("dd.MM.yyyy, HH:mm", Locale.getDefault())
    formatter.format(date)
}