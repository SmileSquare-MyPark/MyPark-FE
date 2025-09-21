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
import mypark.composeapp.generated.resources.enter_nickname
import mypark.composeapp.generated.resources.login_id
import mypark.composeapp.generated.resources.next
import mypark.composeapp.generated.resources.nickname_guide
import mypark.composeapp.generated.resources.required_nickname
import mypark.composeapp.generated.resources.set_nickname
import mypark.composeapp.generated.resources.sign_up
import mypark.composeapp.generated.resources.sign_up_info
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