package com.smile.mypark.presentation.sign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.smile.mypark.core.ext.keyboardHide
import com.smile.mypark.core.ext.toFixedSp
import com.smile.mypark.core.ui.component.Topbar
import com.smile.mypark.core.ui.theme.Black
import com.smile.mypark.core.ui.theme.NeutralGray
import com.smile.mypark.core.ui.theme.White
import com.smile.mypark.presentation.auth.component.BorderedRoundedRect7
import com.smile.mypark.presentation.auth.component.MyparkLoginButton
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.confirm_password
import mypark.composeapp.generated.resources.new_password
import mypark.composeapp.generated.resources.next
import mypark.composeapp.generated.resources.password_sub
import mypark.composeapp.generated.resources.password_title
import mypark.composeapp.generated.resources.register
import mypark.composeapp.generated.resources.require_password
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun SetPasswordRoute(
    padding: PaddingValues,
    navigateNext: (SignStep) -> Unit,
    viewModel: SignViewModel = koinViewModel()
) {
    val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(viewState.isSocial) {
        if (viewState.isSocial) {
            navigateNext(SignStep.NICKNAME)
        }
    }

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { eff ->
            when (eff) {
                is SignContract.SideEffect.NavigateNext -> navigateNext(eff.step)
                is SignContract.SideEffect.Toast -> {
                    // 스낵바/토스트 표시
                }
                else -> Unit
            }
        }
    }

    SetPasswordScreen(
        padding = padding,
        viewState = viewState,
        onEvent = viewModel::setEvent,
    )
}

@Composable
private fun SetPasswordScreen(
    padding: PaddingValues,
    viewState: SignContract.State,
    onEvent: (SignContract.Event) -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(White)
            .padding(bottom = 120.dp, top = 60.dp)
            .keyboardHide(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(Modifier.height(15.dp))
        Topbar(
            title = stringResource(Res.string.register),
            onClick = {}
        )
        Spacer(Modifier.height(40.dp))

        Text(
            text = stringResource(Res.string.password_title),
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 20.toFixedSp(),
                lineHeight = 23.toFixedSp(),
                color = Black
            )
        )
        Spacer(Modifier.height(30.dp))

        Text(
            text = stringResource(Res.string.password_sub),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 14.toFixedSp(),
                lineHeight = 16.toFixedSp(),
                color = NeutralGray
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 50.dp)
        )

        Spacer(Modifier.height(35.dp))

        Text(
            text = stringResource(Res.string.new_password),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 15.toFixedSp(),
                lineHeight = 17.toFixedSp(),
                color = NeutralGray
            )
        )

        Spacer(Modifier.height(17.dp))

        BorderedRoundedRect7(
            value = viewState.password,
            onValueChange = { onEvent(SignContract.Event.PwChanged(it)) },
            placeholder = stringResource(Res.string.require_password),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 13.toFixedSp(),
                lineHeight = 17.toFixedSp(),
                color = Black
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Next
            ),
            visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 40.dp)
            )

        Spacer(Modifier.height(5.dp))

        BorderedRoundedRect7(
            value = viewState.passwordConfirm,
            onValueChange = { onEvent(SignContract.Event.PwConfirmChanged(it)) },
            placeholder = stringResource(Res.string.confirm_password),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 13.toFixedSp(),
                lineHeight = 17.toFixedSp(),
                color = Black
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            ),
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 40.dp)
        )

        Spacer(Modifier.weight(1f))

        MyparkLoginButton(
            text = stringResource(Res.string.next),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 40.dp),
            onClick = { onEvent(SignContract.Event.ClickPasswordNext) },
            enabled = viewState.isPasswordReady
        )
    }
}

@Preview
@Composable
private fun PreviewSetPassword() {
    SetPasswordScreen(
        padding = PaddingValues(),
        viewState = SignContract.State(),
        onEvent = {}
    )
}