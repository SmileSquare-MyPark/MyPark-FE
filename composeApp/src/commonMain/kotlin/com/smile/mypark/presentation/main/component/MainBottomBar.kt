package com.smile.mypark.presentation.main.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smile.mypark.core.ext.noRippleClickable
import com.smile.mypark.core.ext.toFixedSp
import com.smile.mypark.presentation.main.navigation.MainTab
import com.smile.mypark.core.ui.theme.Gray50
import com.smile.mypark.core.ui.theme.Primary
import com.smile.mypark.core.ui.theme.White
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun MainBottomBar(
    modifier: Modifier = Modifier,
    visible: Boolean,
    tabs: List<MainTab>,
    currentTab: MainTab?,
    onTabSelected: (MainTab) -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideIn { IntOffset(0, it.height) },
        exit = fadeOut() + slideOut { IntOffset(0, it.height) }
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .height(82.dp)
                .shadow(16.dp, RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp), clip = false)
                .background(color = White)
        ) {
            tabs.forEach { tab ->
                if (tab == MainTab.QR_F)
                    Spacer(Modifier.weight(1f))
                else
                    MainBottomBarItem(
                        tab = tab,
                        selected = tab == currentTab,
                        onClick = { onTabSelected(tab) },
                    )
            }
        }
    }
}

@Composable
internal fun MainFAB(
    modifier: Modifier = Modifier,
    visible: Boolean,
    tab: MainTab,
    onTabSelected: (MainTab) -> Unit,
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn() + slideIn { IntOffset(0, it.height) },
        exit = fadeOut() + slideOut { IntOffset(0, it.height) }
    ) {
        Icon(
            painter = painterResource(tab.iconResId),
            contentDescription = tab.contentDescription,
            tint = Color.Unspecified,
            modifier = modifier.size(66.dp)
                .noRippleClickable { onTabSelected(tab) }
        )

    }
}

@Composable
private fun RowScope.MainBottomBarItem(
    modifier: Modifier = Modifier,
    tab: MainTab,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .weight(1f)
            .fillMaxHeight()
            .selectable(
                selected = selected,
                indication = null,
                role = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = painterResource(tab.iconResId),
                contentDescription = tab.contentDescription,
                tint = if (selected) {
                    Primary
                } else {
                    Gray50
                },
            )
            Text(
                tab.contentDescription,
                style = MaterialTheme.typography.bodySmall.copy(fontSize = 12.toFixedSp()),
                color = if (selected) {
                    Primary
                } else {
                    Gray50
                },
            )
        }
    }
}

@Preview
@Composable
private fun MainBottomBarPreview() {
    MainBottomBar(
        visible = true,
        tabs = MainTab.entries,
        currentTab = MainTab.HOME,
        onTabSelected = { },
    )
}
