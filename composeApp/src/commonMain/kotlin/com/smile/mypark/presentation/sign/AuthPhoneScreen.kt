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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
import mypark.composeapp.generated.resources.confirm_verification_code
import mypark.composeapp.generated.resources.enter_phone_number
import mypark.composeapp.generated.resources.enter_verification_code
import mypark.composeapp.generated.resources.next
import mypark.composeapp.generated.resources.phone_number_guide
import mypark.composeapp.generated.resources.phone_verification
import mypark.composeapp.generated.resources.register
import mypark.composeapp.generated.resources.send_message
import mypark.composeapp.generated.resources.verification_code
import mypark.composeapp.generated.resources.verification_guide
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel


@Composable
internal fun AuthPhoneRoute(
    padding: PaddingValues,
    navigateNext: (SignStep) -> Unit,
    viewModel: SignViewModel = koinViewModel()
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(state.isSocial) {
        if (state.isSocial) {
            navigateNext(SignStep.NICKNAME)
        }
    }

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { eff ->
            when (eff) {
                is SignContract.SideEffect.NavigateNext -> navigateNext(eff.step)
                is SignContract.SideEffect.Toast -> {

                }
                else -> Unit
            }
        }
    }

    AuthPhoneScreen(
        padding = padding,
        state = state,
        onEvent = viewModel::setEvent,

    )
}

@Composable
private fun AuthPhoneScreen(
    padding: PaddingValues,
    state: SignContract.State,
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
            text = stringResource(Res.string.verification_guide),
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 20.toFixedSp(),
                lineHeight = 23.toFixedSp(),
                color = Black
            )
        )
        Spacer(Modifier.height(30.dp))

        Text(
            text = stringResource(Res.string.phone_number_guide),
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
            text = stringResource(Res.string.phone_verification),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 15.toFixedSp(),
                lineHeight = 17.toFixedSp(),
                color = NeutralGray
            )
        )

        Spacer(Modifier.height(15.dp))

        BorderedRoundedRect7(
            value = state.phoneNumber,
            onValueChange = { onEvent(SignContract.Event.PhoneChanged(it)) },
            placeholder = stringResource(Res.string.enter_phone_number),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 13.toFixedSp(),
                lineHeight = 17.toFixedSp(),
                color = Black
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 40.dp)
        )

        Spacer(Modifier.height(8.dp))

        MyparkLoginButton(
            text = stringResource(Res.string.send_message),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 40.dp),
            onClick = {         println("[UI] Send button clicked")
                onEvent(SignContract.Event.ClickSendCode) },
            enabled = state.phoneNumber.isNotBlank() && !state.phoneLoading
        )

        Spacer(Modifier.height(44.dp))

        Text(
            text = stringResource(Res.string.verification_code),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 15.toFixedSp(),
                lineHeight = 17.toFixedSp(),
                color = NeutralGray
            )
        )

        Spacer(Modifier.height(14.dp))

        BorderedRoundedRect7(
            value = state.verificationCode,
            onValueChange = { onEvent(SignContract.Event.CodeChanged(it)) },
            placeholder = stringResource(Res.string.enter_verification_code),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 13.toFixedSp(),
                lineHeight = 17.toFixedSp(),
                color = Black
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 40.dp)
        )

        Spacer(Modifier.height(9.dp))

        MyparkLoginButton(
            text = stringResource(Res.string.confirm_verification_code),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 40.dp),
            onClick = { onEvent(SignContract.Event.ClickVerifyCode) },
            enabled = state.verificationCode.length >= 4 && !state.phoneLoading

        )

        Spacer(Modifier.weight(1f))

        MyparkLoginButton(
            text = stringResource(Res.string.next),
            onClick = { onEvent(SignContract.Event.ClickPhoneNext) },
            enabled = state.phoneVerified,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 40.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewSign() {
    AuthPhoneScreen(
        padding = PaddingValues(),
        state = SignContract.State(),
        onEvent = {}
    )
}