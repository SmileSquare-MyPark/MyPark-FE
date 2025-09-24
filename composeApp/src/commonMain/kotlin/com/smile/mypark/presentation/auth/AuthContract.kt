package com.smile.mypark.presentation.auth

import com.smile.mypark.core.base.ViewEvent
import com.smile.mypark.core.base.ViewSideEffect
import com.smile.mypark.core.base.ViewState

class AuthContract {
    data class AuthState(
        val id: String = "",
        val pw: String = "",
        val autoLogin: Boolean = false,
        val autoIdLogin: Boolean = false,
        val loading: Boolean = false,
        val error: String? = null
    ) : ViewState

    sealed interface AuthEvent : ViewEvent {
        data class IdChanged(val v: String) : AuthEvent
        data class PwChanged(val v: String) : AuthEvent
        object ToggleAutoLogin : AuthEvent
        object ToggleAutoIdLogin : AuthEvent
        object ClickLogin : AuthEvent
        object ClickSignUp : AuthEvent
        object ClickFindIdPw : AuthEvent
    }

    sealed interface AuthSideEffect : ViewSideEffect {
        object NavigateHome : AuthSideEffect
        object NavigateSignup : AuthSideEffect
        object NavigateFindIdPw : AuthSideEffect
        data class Toast(val msg: String) : AuthSideEffect
    }
}