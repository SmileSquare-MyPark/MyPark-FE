package com.smile.mypark.presentation.auth

import androidx.lifecycle.viewModelScope
import com.smile.mypark.core.base.BaseViewModel
import com.smile.mypark.domain.usecase.LoginUseCase
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase
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
}