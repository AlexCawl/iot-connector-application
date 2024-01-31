package org.alexcawl.iot_connector.ui.theme.extended

import androidx.compose.runtime.Immutable
import org.alexcawl.iot_connector.ui.theme.extended.sizes.CommonSizes
import org.alexcawl.iot_connector.ui.theme.extended.sizes.IconSizes
import org.alexcawl.iot_connector.ui.theme.extended.sizes.PaddingSizes

@Immutable
data class ExtendedSizes(
    val icons: IconSizes = IconSizes,
    val paddings: PaddingSizes = PaddingSizes,
    val common: CommonSizes = CommonSizes
)

