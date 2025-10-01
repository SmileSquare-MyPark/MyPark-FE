package com.smile.mypark.presentation.auth

import androidx.lifecycle.viewModelScope
import com.smile.mypark.core.base.BaseViewModel
import com.smile.mypark.domain.usecase.LoginUseCase
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val kakaoGateway: KakaoLoginGateway,
    private val naverGateway: NaverLoginGateway
): BaseViewModel <AuthContract.AuthState, AuthContract.AuthSideEffect, AuthContract.AuthEvent>(
    AuthContract.AuthState()
) {
    override fun handleEvents(event: AuthContract.AuthEvent) {
        when (event) {
            is AuthContract.AuthEvent.IdChanged -> updateState { copy(id = event.v, error = null) }
            is AuthContract.AuthEvent.PwChanged -> updateState { copy(pw = event.v, error = null) }
            AuthContract.AuthEvent.ToggleAutoLogin -> updateState { copy(autoLogin = !autoLogin) }
            AuthContract.AuthEvent.ToggleAutoIdLogin -> updateState { copy(autoIdLogin = !autoIdLogin) }
            AuthContract.AuthEvent.ClickLogin -> login()
            AuthContract.AuthEvent.ClickSignUp -> sendEffect ({ AuthContract.AuthSideEffect.NavigateSignup })
            AuthContract.AuthEvent.ClickFindIdPw -> sendEffect ({ AuthContract.AuthSideEffect.NavigateFindIdPw })
            AuthContract.AuthEvent.ClickKakaoLogin -> loginWithKakao()
            AuthContract.AuthEvent.ClickNaverLogin -> loginNaver()
        }
    }


    private fun login() = viewModelScope.launch {
        val s = viewState.value
        if (s.id.isBlank() || s.pw.isBlank()) {
            sendEffect ({ AuthContract.AuthSideEffect.Toast("아이디/비밀번호를 입력해 주세요") })
            return@launch
        }
        println("[Login] start id=${s.id}")

        updateState { copy(loading = true, error = null) }

        runCatching { loginUseCase(s.id, s.pw) }
            .onSuccess {
                updateState { copy(loading = false, pw = "") }
                sendEffect({ AuthContract.AuthSideEffect.NavigateHome })
            }
            .onFailure { t ->
                println("[Login] fail id=${s.id} error=${t.message}")
                updateState { copy(loading = false, error = t.message) }
                sendEffect({ AuthContract.AuthSideEffect.Toast(t.message ?: "로그인 실패") })
            }
    }

    private fun loginWithKakao() = viewModelScope.launch {
        updateState { copy(isLoading = true, error = null) }
        runCatching { kakaoGateway.login() }
            .onSuccess { token ->
                updateState { copy(isLoading = false, token = token) }
                sendEffect ({ AuthContract.AuthSideEffect.Toast(msg = "카카오 로그인 성공") })
                sendEffect ({ AuthContract.AuthSideEffect.NavigateHome })
            }
            .onFailure { e ->
                updateState { copy(isLoading = false, error = e.message) }
                sendEffect ({ AuthContract.AuthSideEffect.Toast("카카오 로그인 실패: ${e.message}") })
            }
    }

    private fun loginNaver() = viewModelScope.launch {
        updateState { copy(isLoading = true, error = null) }
        runCatching { naverGateway.login() }
            .onSuccess { accessToken ->
                updateState { copy(isLoading = false, token = accessToken) }
                sendEffect ({ AuthContract.AuthSideEffect.NavigateHome })
            }
            .onFailure { e ->
                updateState { copy(isLoading = false, error = e.message) }
                sendEffect ({ AuthContract.AuthSideEffect.Toast(e.message ?: "네이버 로그인 실패") })
            }
    }
}