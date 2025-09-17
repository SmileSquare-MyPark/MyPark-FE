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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smile.mypark.presentation.auth.component.AuthSubtitle
import com.smile.mypark.presentation.auth.component.MyParkLogo
import com.smile.mypark.presentation.auth.component.MyparkLoginButton
import com.smile.mypark.presentation.auth.component.SocialCircleIcon
import com.smile.mypark.ui.theme.NanumTypography
import com.smile.mypark.ui.theme.NeutralGray
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.copyright
import mypark.composeapp.generated.resources.login
import mypark.composeapp.generated.resources.login_comfortable
import mypark.composeapp.generated.resources.login_main_title
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun AuthRoute(
    padding: PaddingValues,
    onClickSign: () -> Unit,
    onClickKakao: () -> Unit = {},
    onClickNaver: () -> Unit = {},
    //viewModel: AuthViewModel = hiltViewModel()
) {
    //val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    AuthScreen(
        padding = padding,
        //viewState = viewState,
        //onClickDetail = onClickDetail
        onClickSign = onClickSign,
        onClickKakao = onClickKakao,
        onClickNaver = onClickNaver
    )
}

@Composable
private fun AuthScreen(
    padding: PaddingValues,
    //viewState: AuthContract.AuthViewState,
    onClickSign: () -> Unit,
    onClickKakao: () -> Unit,
    onClickNaver: () -> Unit,
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
                onClick = onClickSign,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(horizontal = 20.dp)
            )

            Spacer(Modifier.height(50.dp))

            Text(
                text = stringResource(Res.string.login_comfortable),
                style = NanumTypography().titleMediumB,
                color = NeutralGray,
            )

            Spacer(Modifier.height(50.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                SocialCircleIcon(
                    bg = Color(0xFFFEE500),
                    onClick = onClickKakao
                ) { Text("K", color = Color.Black) }

                SocialCircleIcon(
                    bg = Color(0xFF03C75A),
                    onClick = onClickNaver
                ) { Text("N", color = Color.White) }
            }
        }

        Text(
            text = stringResource(Res.string.copyright),
            style = NanumTypography().bodySmallR,
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
//        viewState = AuthContract.AuthViewState(),
        onClickSign = {},
        onClickKakao = {},
        onClickNaver = {}
    )
}