package com.smile.mypark.presentation.sign

import com.smile.mypark.core.base.ViewEvent
import com.smile.mypark.core.base.ViewSideEffect
import com.smile.mypark.core.base.ViewState

class SignContract {
    data class State(
        val allChecked: Boolean = false,
        val required: List<Boolean> = listOf(false, false, false, false), // 필수 항목 개수에 맞게
        val optional: List<Boolean> = listOf(false, false, false),        // 선택 항목 개수에 맞게

        val uid: String = "",
        val password: String = "",
        val passwordConfirm: String = "",
        val nickname: String = "",

        val phoneNumber: String = "",
        val verificationCode: String = "",
        val phoneLoading: Boolean = false,
        val phoneVerified: Boolean = false
    ) : ViewState {
        val isNextEnabled: Boolean get() = required.all { it }
        val isPasswordReady: Boolean get() = password.isNotBlank() && passwordConfirm.isNotBlank() && password == passwordConfirm
        val isNicknameReady: Boolean get() = nickname.isNotBlank()
    }

    sealed interface Event : ViewEvent {
        data object ToggleAll : Event
        data class ToggleRequired(val index: Int) : Event
        data class ToggleOptional(val index: Int) : Event
        data object ClickNext : Event
        data class ClickDetailRequired(val index: Int) : Event
        data class ClickDetailOptional(val index: Int) : Event
        data class UidChanged(val uid: String) : Event
        data class NicknameChanged(val nickname: String) : Event
        data object ClickNicknameNext : Event


        data class PhoneChanged(val v: String) : Event
        data class CodeChanged(val v: String) : Event

        data object ClickPhoneNext : Event
        data object ClickSendCode : Event
        data object ClickVerifyCode : Event

        data class PwChanged(val pw: String) : Event
        data class PwConfirmChanged(val pw: String) : Event
        data object ClickPasswordNext : Event
    }

    sealed interface SideEffect : ViewSideEffect {
        data object NavigateNext : SideEffect
        data class ShowTermsDetail(val type: TermsType, val index: Int) : SideEffect
        enum class TermsType { REQUIRED, OPTIONAL }
        data class Toast(val msg: String) : SideEffect
    }
}
