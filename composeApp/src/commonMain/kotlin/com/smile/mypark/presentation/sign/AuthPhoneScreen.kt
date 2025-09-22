package com.smile.mypark.presentation.sign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smile.mypark.core.ext.toFixedSp
import com.smile.mypark.core.ui.component.Topbar
import com.smile.mypark.core.ui.theme.Black
import com.smile.mypark.core.ui.theme.LightGray179
import com.smile.mypark.core.ui.theme.NeutralGray
import com.smile.mypark.core.ui.theme.White
import com.smile.mypark.presentation.auth.component.BorderedRoundedRect7
import com.smile.mypark.presentation.auth.component.MyparkLoginButton
import com.smile.mypark.presentation.auth.component.RoundedRect7
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.confirm_verification_code
import mypark.composeapp.generated.resources.enter_nickname
import mypark.composeapp.generated.resources.enter_phone_number
import mypark.composeapp.generated.resources.enter_verification_code
import mypark.composeapp.generated.resources.login_id
import mypark.composeapp.generated.resources.next
import mypark.composeapp.generated.resources.nickname_guide
import mypark.composeapp.generated.resources.phone_number_guide
import mypark.composeapp.generated.resources.phone_verification
import mypark.composeapp.generated.resources.required_nickname
import mypark.composeapp.generated.resources.send_message
import mypark.composeapp.generated.resources.set_nickname
import mypark.composeapp.generated.resources.sign_up
import mypark.composeapp.generated.resources.sign_up_info
import mypark.composeapp.generated.resources.verification_code
import mypark.composeapp.generated.resources.verification_guide
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
internal fun AuthPhoneRoute(
    padding: PaddingValues,
    onClick: () -> Unit
//    onClickDetail: () -> Unit,
    //viewModel: SignViewModel = hiltViewModel()
) {
    //val viewState by viewModel.viewState.collectAsStateWithLifecycle()

    AuthPhoneScreen(
        padding = padding,
        onClick = onClick
        //viewState = viewState,
        //onClickDetail = onClickDetail
    )
}

@Composable
private fun AuthPhoneScreen(
    padding: PaddingValues,
    onClick: () -> Unit
    //viewState: SignContract.SignViewState,
    ///onClickDetail: () -> Unit,
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
            title = stringResource(Res.string.set_nickname),
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

        var phoneNumber by rememberSaveable { mutableStateOf("") }

        BorderedRoundedRect7(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            placeholder = stringResource(Res.string.enter_phone_number),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 13.toFixedSp(),
                lineHeight = 17.toFixedSp(),
                color = LightGray179
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 40.dp)
        )

        Spacer(Modifier.height(8.dp))

        MyparkLoginButton(
            text = stringResource(Res.string.send_message),
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 40.dp)
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

        var verificationCode by rememberSaveable { mutableStateOf("") }

        BorderedRoundedRect7(
            value = verificationCode,
            onValueChange = { verificationCode = it },
            placeholder = stringResource(Res.string.enter_verification_code),
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 13.toFixedSp(),
                lineHeight = 17.toFixedSp(),
                color = LightGray179
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 40.dp)
        )

        Spacer(Modifier.height(9.dp))

        MyparkLoginButton(
            text = stringResource(Res.string.confirm_verification_code),
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 40.dp)
        )

        Spacer(Modifier.weight(1f))

        MyparkLoginButton(
            text = stringResource(Res.string.next),
            onClick = onClick,
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
        onClick = {}
//        viewState = SignContract.SignViewState(),
//        onClickDetail = {}
    )
}