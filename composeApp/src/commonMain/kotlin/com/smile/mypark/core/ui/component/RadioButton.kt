package com.smile.mypark.core.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.unit.dp
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.ic_radio_off
import mypark.composeapp.generated.resources.ic_radio_on
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun CustomRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    selectedIcon: DrawableResource = Res.drawable.ic_radio_on,
    unselectedIcon: DrawableResource = Res.drawable.ic_radio_off
) {
    val iconRes = if (selected) selectedIcon else unselectedIcon

    Icon(
        painter = painterResource(iconRes),
        contentDescription = null,
        tint = Color.Unspecified,
        modifier = modifier
            .clickable { onClick() }
    )
}

@Preview
@Composable
fun CustomRadioButtonPreview() {
    Column {
        CustomRadioButton(
            selected = true,
            onClick = {},
            modifier = Modifier.size(20.dp)
        )
        Spacer(Modifier.height(8.dp))
        CustomRadioButton(
            selected = false,
            onClick = {},
            modifier = Modifier.size(20.dp)
        )
    }

}