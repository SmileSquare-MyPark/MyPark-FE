package com.smile.mypark.presentation.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.smile.mypark.core.ext.toFixedSp
import com.smile.mypark.core.ui.theme.NeutralGray
import com.smile.mypark.presentation.auth.component.AuthSubtitle
import com.smile.mypark.presentation.auth.component.MyParkLogo
import com.smile.mypark.presentation.auth.component.MyparkLoginButton
import com.smile.mypark.presentation.auth.component.SocialCircleIcon
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.copyright
import mypark.composeapp.generated.resources.login
import mypark.composeapp.generated.resources.login_comfortable
import mypark.composeapp.generated.resources.login_main_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun AuthRoute(
    padding: PaddingValues,
    navigateLogin: () -> Unit,
    viewModel: AuthViewModel = koinViewModel()
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { eff ->
            when (eff) {
                is AuthContract.AuthSideEffect.Toast -> {
                    println("[Auth] Toast: ${eff.msg}")
                }
                AuthContract.AuthSideEffect.NavigateFindIdPw -> { /* TODO */ }
                AuthContract.AuthSideEffect.NavigateHome -> { /* TODO */ }
                AuthContract.AuthSideEffect.NavigateSignup -> { /* TODO */ }
            }
        }
    }


    AuthScreen(
        padding = padding,
        state = state,
        onEvent = viewModel::setEvent,
        navigateLogin = navigateLogin
    )
}

@Composable
private fun AuthScreen(
    padding: PaddingValues,
    state: AuthContract.AuthState,
    onEvent: (AuthContract.AuthEvent) -> Unit,
    navigateLogin: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .windowInsetsPadding(WindowInsets.systemBars)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .padding(horizontal = 24.dp)
                .widthIn(max = 360.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            MyParkLogo(Modifier.widthIn(max = 220.dp))

            Spacer(Modifier.height(30.dp))

            AuthSubtitle(stringResource(Res.string.login_main_title))

            Spacer(Modifier.height(50.dp))

            MyparkLoginButton(
                text = stringResource(Res.string.login),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .padding(horizontal = 30.dp),
                onClick = navigateLogin,
                enabled = true
            )

            Spacer(Modifier.height(50.dp))

            Text(
                text = stringResource(Res.string.login_comfortable),
                style = MaterialTheme.typography.bodyLarge.copy(fontSize = 14.toFixedSp(), lineHeight = 16.toFixedSp()),
                color = NeutralGray,
            )

            Spacer(Modifier.height(50.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SocialCircleIcon(
                    bg = Color(0xFFFEE500),
                    onClick = { onEvent(AuthContract.AuthEvent.ClickKakaoLogin) }
                ) { Text("K", color = Color.Black) }

                // Naver
                SocialCircleIcon(
                    bg = Color(0xFF03C75A),
                    onClick = { onEvent(AuthContract.AuthEvent.ClickNaverLogin) }
                ) { Text("N", color = Color.White) }
            }
        }

        Text(
            text = stringResource(Res.string.copyright),
            style = MaterialTheme.typography.bodyMedium.copy(fontSize = 10.toFixedSp(), lineHeight = 11.toFixedSp()),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
        )
    }
}

@Preview
@Composable
private fun PreviewAuth() {
    AuthScreen(
        padding = PaddingValues(),
        navigateLogin = {},
        onEvent = {},
        state = AuthContract.AuthState()
    )
}