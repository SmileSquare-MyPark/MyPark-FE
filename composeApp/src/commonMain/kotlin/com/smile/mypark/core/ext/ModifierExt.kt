package com.smile.mypark.core.ext

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce

fun Modifier.noRippleClickable(onClick: () -> Unit): Modifier = this.composed {
    clickable(
        onClick = onClick,
        indication = null,
        interactionSource = remember { MutableInteractionSource() }
    )
}

fun Modifier.noRippleSingleClickable(
    onClick: () -> Unit,
): Modifier = this.composed {
    singleClickEvent { singleEvent ->
        clickable(
            onClick = { singleEvent.event { onClick() } },
            indication = null,
            interactionSource = remember { MutableInteractionSource() }
        )
    }
}

fun Modifier.singleClickable(
    onClick: () -> Unit,
) = this.composed {
    singleClickEvent { singleEvent ->
        clickable(
            onClick = { singleEvent.event { onClick() } },
        )
    }

    singleClickEvent(content = { singleEvent ->
        clickable(
            onClick = { singleEvent.event { onClick() } },
        )
    })
}

interface SingleClickEventInterface {
    fun event(event: () -> Unit)
}

@OptIn(FlowPreview::class)
@Composable
fun <T> singleClickEvent(
    content: @Composable (SingleClickEventInterface) -> T,
): T {
    val debounceState = remember {
        MutableSharedFlow<() -> Unit>(
            replay = 0,
            extraBufferCapacity = 1,
            onBufferOverflow = BufferOverflow.DROP_OLDEST
        )
    }

    val result = content(
        object : SingleClickEventInterface {
            override fun event(event: () -> Unit) {
                debounceState.tryEmit(event)
//                debounceState.emit(event)
            }
        }
    )

    LaunchedEffect(true) {
        debounceState
            .debounce(300L)
            .collect { onClick ->
                onClick.invoke()
            }
    }

    return result
}

fun Modifier.keyboardHide(): Modifier = composed {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    pointerInput(Unit) {
        detectTapGestures(onTap = {
            focusManager.clearFocus(force = true)
            keyboardController?.hide()
        })
    }
}
