package com.smile.mypark.presentation.sign

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.smile.mypark.core.ext.noRippleSingleClickable
import com.smile.mypark.core.ext.toFixedSp
import com.smile.mypark.core.ui.component.CustomRadioButton
import com.smile.mypark.core.ui.component.Topbar
import com.smile.mypark.core.ui.theme.Black
import com.smile.mypark.core.ui.theme.NeutralGray
import com.smile.mypark.core.ui.theme.VeryLightGray230
import com.smile.mypark.core.ui.theme.White
import com.smile.mypark.presentation.auth.component.MyparkLoginButton
import com.smile.mypark.presentation.auth.component.RoundedRect7
import com.smile.mypark.presentation.sign.component.TermsList
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.agreement
import mypark.composeapp.generated.resources.ic_radio_off
import mypark.composeapp.generated.resources.ic_radio_on
import mypark.composeapp.generated.resources.marketing_advertising_use
import mypark.composeapp.generated.resources.next
import mypark.composeapp.generated.resources.privacy_collection_usage_details
import mypark.composeapp.generated.resources.privacy_third_party_provision
import mypark.composeapp.generated.resources.sign_up
import mypark.composeapp.generated.resources.sign_up_info
import mypark.composeapp.generated.resources.sns_receive
import mypark.composeapp.generated.resources.terms_location
import mypark.composeapp.generated.resources.terms_unified
import mypark.composeapp.generated.resources.use_kakao_profile
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun AgreementRoute(
    padding: PaddingValues,
    navigateNext: () -> Unit,
    viewModel: SignViewModel = koinViewModel()
) {
    val state by viewModel.viewState.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.effect.collect { eff ->
            when (eff) {
                SignContract.SideEffect.NavigateNext -> navigateNext()
                is SignContract.SideEffect.ShowTermsDetail -> {
                    // 약관 상세 화면
                }
                is SignContract.SideEffect.Toast -> {
                    // 스낵바/토스트
                }
            }
        }
    }


    AgreementScreen(
        padding = padding,
        state = state,
        onEvent = viewModel::setEvent
    )
}

@Composable
private fun AgreementScreen(
    padding: PaddingValues,
    state: SignContract.State,
    onEvent: (SignContract.Event) -> Unit
) {
    val titlesRequired = listOf(
        stringResource(Res.string.terms_unified),
        stringResource(Res.string.privacy_collection_usage_details),
        stringResource(Res.string.terms_location),
        stringResource(Res.string.privacy_third_party_provision)
    )
    val titlesOptional = listOf(
        stringResource(Res.string.marketing_advertising_use),
        stringResource(Res.string.sns_receive),
        stringResource(Res.string.use_kakao_profile),
    )

    Column(
        Modifier
            .fillMaxSize()
            .background(White)
            .padding(bottom = 120.dp, top = 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Spacer(Modifier.height(15.dp))
        Topbar(
            title = stringResource(Res.string.agreement),
            onClick = {}
        )
        Spacer(Modifier.height(40.dp))

        Text(
            text = stringResource(Res.string.sign_up),
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 20.toFixedSp(),
                lineHeight = 23.toFixedSp(),
                color = Black
            )
        )
        Spacer(Modifier.height(30.dp))

        Text(
            text = stringResource(Res.string.sign_up_info),
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 14.toFixedSp(),
                lineHeight = 16.toFixedSp(),
                color = NeutralGray
            ),
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(horizontal = 70.dp)
        )

        Spacer(Modifier.height(13.dp))

        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            color = White
        ) {
            RoundedRect7(
                modifier = Modifier
                    .height(48.dp)
                    .noRippleSingleClickable { onEvent(SignContract.Event.ToggleAll) }
                    .padding(horizontal = 40.dp),
                VeryLightGray230,
                padding = PaddingValues(vertical = 0.dp)

            ) {
                Row(
                    Modifier
                        .fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    CustomRadioButton(
                        selected = state.allChecked,
                        onClick = { onEvent(SignContract.Event.ToggleAll) },
                        selectedIcon = Res.drawable.ic_radio_on,
                        unselectedIcon = Res.drawable.ic_radio_off,
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .size(25.dp)
                    )
                    Spacer(Modifier.width(9.dp))
                    Text(
                        text = "전체 동의",
                        style = MaterialTheme.typography.headlineLarge.copy(
                            fontSize = 15.toFixedSp(),
                            lineHeight = 17.toFixedSp(),
                            color = Black
                        ),
                        modifier = Modifier.padding(bottom = 1.dp)
                    )
                }

            }

        }
        Spacer(Modifier.height(26.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 52.dp, end = 40.dp)
        ) {
            // 필수
            titlesRequired.forEachIndexed { i, title ->
                TermsList(
                    title = title,
                    selected = state.required[i],
                    onToggle = { onEvent(SignContract.Event.ToggleRequired(i)) },
                    onClickDetail = { onEvent(SignContract.Event.ClickDetailRequired(i)) }
                )
            }

            Spacer(Modifier.height(35.dp))

            // 선택
            titlesOptional.forEachIndexed { i, title ->
                TermsList(
                    title = title,
                    selected = state.optional[i],
                    onToggle = { onEvent(SignContract.Event.ToggleOptional(i)) },
                    onClickDetail = { onEvent(SignContract.Event.ClickDetailOptional(i)) },
                    showSeeMore = false
                )
            }
        }
        Spacer(Modifier.weight(1f))

        MyparkLoginButton(
            text = stringResource(Res.string.next),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 40.dp),
            onClick = { onEvent(SignContract.Event.ClickNext) },
            enabled = state.isNextEnabled
        )
    }
}

@Preview
@Composable
private fun PreviewAgreement() {
    AgreementScreen(
        padding = PaddingValues(),
        state = SignContract.State(),
        onEvent = {}
    )
}