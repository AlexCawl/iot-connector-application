package org.alexcawl.iot_connector.common.model

import org.alexcawl.iot_connector.common.util.SignificantString

@JvmInline
value class Password(override val content: String) : SignificantString