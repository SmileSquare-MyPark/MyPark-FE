package com.smile.mypark.presentation.sign

import com.smile.mypark.core.base.ViewEvent
import com.smile.mypark.core.base.ViewSideEffect
import com.smile.mypark.core.base.ViewState


class SignContract {
    data class State(
        val allChecked: Boolean = false,
        val required: Map<RequiredTerm, Boolean> = RequiredTerm.entries.associateWith { false },
        val optional: Map<OptionalTerm, Boolean> = OptionalTerm.entries.associateWith { false },

        val uid: String = "",
        val uidX: String? = null,
        val socialProvider: SocialProvider? = null,

        val password: String = "",
        val passwordConfirm: String = "",
        val nickname: String = "",

        val phoneNumber: String = "",
        val verificationCode: String = "",
        val phoneLoading: Boolean = false,
        val phoneVerified: Boolean = false,
        val registerLoading: Boolean = false,

        val signupLoading: Boolean = false,
        val signupSucceeded: Boolean? = null,
        val signupError: String? = null
    ) : ViewState {
        val isNextEnabled: Boolean get() = required.values.all { it }
        val isPasswordReady: Boolean get() = password.isNotBlank() && passwordConfirm.isNotBlank() && password == passwordConfirm
        val isNicknameReady: Boolean get() = nickname.isNotBlank()
        val isSocial: Boolean get() = uidX != null
    }

    sealed interface Event : ViewEvent {
        data object ToggleAll : Event
        data class ToggleRequired(val key: RequiredTerm) : Event
        data class ToggleOptional(val key: OptionalTerm) : Event
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

        data object ClickRegister : Event
    }

    sealed interface SideEffect : ViewSideEffect {
        data class NavigateNext(val step: SignStep) : SideEffect
        data class ShowTermsDetail(val type: TermsType, val index: Int) : SideEffect
        enum class TermsType { REQUIRED, OPTIONAL }
        data class Toast(val msg: String) : SideEffect
    }
}

enum class RequiredTerm { UNIFIED, PRIVACY, LOCATION, THIRD_PARTY }
enum class OptionalTerm { MARKETING, SNS, KAKAO_PROFILE }
enum class SocialProvider { KAKAO, NAVER }
enum class SignStep { AGREEMENT, PHONE, PASSWORD, NICKNAME, WELCOME }
