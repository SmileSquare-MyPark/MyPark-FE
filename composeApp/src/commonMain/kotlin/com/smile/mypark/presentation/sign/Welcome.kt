package com.smile.mypark.presentation.sign

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.smile.mypark.core.ext.toFixedSp
import com.smile.mypark.core.ui.component.Topbar
import com.smile.mypark.core.ui.theme.Black
import com.smile.mypark.core.ui.theme.NeutralGray
import com.smile.mypark.core.ui.theme.White
import com.smile.mypark.presentation.auth.component.MyparkLoginButton
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.complete_sign_up
import mypark.composeapp.generated.resources.confirm
import mypark.composeapp.generated.resources.ic_complete_sign_up
import mypark.composeapp.generated.resources.welcome_app
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun WelcomeRoute(
    padding: PaddingValues,
    onClick: () -> Unit,
    navigateToLogin: () -> Unit,
    viewModel: SignViewModel = koinViewModel()
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.setEvent(SignContract.Event.ClickRegister)
    }

    when {
        state.signupLoading -> SignUpLoadingScreen(padding)
        state.signupSucceeded == true -> WelcomeScreen(padding, onClick)
        state.signupSucceeded == false -> SignUpFailedScreen(
            padding = padding,
            message = state.signupError ?: "회원가입 실패",
            onRetry = navigateToLogin
        )
        else -> SignUpLoadingScreen(padding)
    }

}

@Composable
private fun WelcomeScreen(
    padding: PaddingValues,
    onClick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(White)
            .padding(bottom = 120.dp, top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(Modifier.height(15.dp))
        Topbar(
            title = "",
            onClick = {}
        )
        Spacer(Modifier.height(40.dp))

        Text(
            text = stringResource(Res.string.complete_sign_up),
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 20.toFixedSp(),
                lineHeight = 23.toFixedSp(),
                color = Black
            ),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(110.dp))

        Image(
            painter = painterResource(Res.drawable.ic_complete_sign_up),
            contentDescription = stringResource(Res.string.complete_sign_up)
        )

        Spacer(Modifier.height(35.dp))

        Text(
            text = stringResource(Res.string.welcome_app),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 14.toFixedSp(),
                lineHeight = 16.toFixedSp(),
                color = NeutralGray
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 50.dp)
        )

        Spacer(Modifier.weight(1f))

        MyparkLoginButton(
            text = stringResource(Res.string.confirm),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 40.dp),
            onClick = onClick,
            enabled = true
        )
    }
}

@Composable
private fun SignUpFailedScreen(
    padding: PaddingValues,
    message: String,
    onRetry: () -> Unit
) {
    Column(
        Modifier.fillMaxSize().background(White).padding(bottom = 120.dp, top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(Modifier.height(40.dp))
        Text(
            text = "회원가입에 실패했어요",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 20.toFixedSp(),
                lineHeight = 23.toFixedSp(),
                color = Black
            ),
            textAlign = TextAlign.Center
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = message,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 14.toFixedSp(),
                lineHeight = 16.toFixedSp(),
                color = NeutralGray
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 40.dp)
        )
        Spacer(Modifier.weight(1f))
        MyparkLoginButton(
            text = "다시 시도",
            onClick = onRetry,
            modifier = Modifier.fillMaxWidth().height(50.dp).padding(horizontal = 40.dp),
            enabled = true
        )
    }
}

@Composable
private fun SignUpLoadingScreen(padding: PaddingValues) {
    Box(
        Modifier.fillMaxSize().background(White).padding(padding),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Preview
@Composable
private fun PreviewWelcome() {
    WelcomeScreen(
        padding = PaddingValues(),
        onClick = {  }
    )
}
@Preview
@Composable
private fun PreviewSignUpFailed() {
    SignUpFailedScreen(
        padding = PaddingValues(),
        message = "회원가입 실패",
        onRetry = {}
    )
}

@Preview
@Composable
private fun PreviewSignUpLoading() {
    SignUpLoadingScreen(
        padding = PaddingValues()
    )
}