package com.smile.mypark.presentation.sign

import androidx.lifecycle.viewModelScope
import com.smile.mypark.core.base.BaseViewModel
import com.smile.mypark.domain.model.RegisterInfo
import com.smile.mypark.domain.model.SignStartArgs
import com.smile.mypark.domain.usecase.RegisterUseCase
import com.smile.mypark.domain.usecase.SendVerificationCodeUseCase
import com.smile.mypark.domain.usecase.VerifyCodeUseCase
import com.smile.mypark.presentation.sign.SignContract.SideEffect.*
import kotlinx.coroutines.launch

class SignViewModel (
    private val sendCode: SendVerificationCodeUseCase,
    private val verifyCode: VerifyCodeUseCase,
    private val register: RegisterUseCase
) : BaseViewModel<SignContract.State, SignContract.SideEffect, SignContract.Event>(
    SignContract.State()
) {
    override fun handleEvents(event: SignContract.Event) {
        when (event) {
            SignContract.Event.ToggleAll -> toggleAll()
            is SignContract.Event.ToggleRequired -> toggleRequired(event.key)
            is SignContract.Event.ToggleOptional -> toggleOptional(event.key)
            SignContract.Event.ClickNext -> agreementNext()
            is SignContract.Event.ClickDetailRequired ->
                sendEffect ({ ShowTermsDetail(SignContract.SideEffect.TermsType.REQUIRED, event.index) })
            is SignContract.Event.ClickDetailOptional ->
                sendEffect ({ ShowTermsDetail(SignContract.SideEffect.TermsType.OPTIONAL, event.index) })

            is SignContract.Event.UidChanged -> updateState { copy(uid = event.uid) }
            is SignContract.Event.NicknameChanged -> updateState { copy(nickname = event.nickname) }
            SignContract.Event.ClickNicknameNext -> onNicknameNext()

            is SignContract.Event.PhoneChanged -> updateState { copy(phoneNumber = event.v) }
            is SignContract.Event.CodeChanged  -> updateState { copy(verificationCode = event.v) }
            SignContract.Event.ClickSendCode -> { println("[VM] ClickSendCode received")
                sendCode()
            }
            SignContract.Event.ClickPhoneNext  -> phoneNext()
            SignContract.Event.ClickVerifyCode -> verifyCode()

            is SignContract.Event.PwChanged -> updateState { copy(password = event.pw) }
            is SignContract.Event.PwConfirmChanged -> updateState { copy(passwordConfirm = event.pw) }
            SignContract.Event.ClickPasswordNext -> passwordNext()

            SignContract.Event.ClickRegister -> signUp()
        }
    }


    private fun toggleAll() {
        val newValue = !viewState.value.allChecked
        updateState {
            copy(
                allChecked = newValue,
                required = required.mapValues { newValue },
                optional = optional.mapValues { newValue }
            )
        }
    }

    private fun toggleRequired(key: RequiredTerm) {
        val s = viewState.value
        val updated = s.required.toMutableMap().apply { this[key] = !(this[key] ?: false) }
        val all = updated.values.all { it } && s.optional.values.all { it }
        updateState { copy(required = updated, allChecked = all) }
    }
    private fun toggleOptional(key: OptionalTerm) {
        val s = viewState.value
        val updated = s.optional.toMutableMap().apply { this[key] = !(this[key] ?: false) }
        val all = s.required.values.all { it } && updated.values.all { it }
        updateState { copy(optional = updated, allChecked = all) }
    }

    private fun agreementNext() {
        val s = viewState.value
        if (!s.isNextEnabled) {
            sendEffect ({ SignContract.SideEffect.Toast("필수 약관에 동의해 주세요.") })
            return
        }
        if (s.isSocial) {
            // ★ 소셜 로그인: 동의 → 바로 닉네임
            sendEffect ({ SignContract.SideEffect.NavigateNext(SignStep.NICKNAME) })
        } else {
            // 일반: 동의 → 휴대폰 인증
            sendEffect ({ SignContract.SideEffect.NavigateNext(SignStep.PHONE) })
        }
    }

    private fun phoneNext() {
        val s = viewState.value
        if (s.phoneVerified) {
            sendEffect ({ SignContract.SideEffect.NavigateNext(SignStep.PASSWORD) })
        } else {
            sendEffect ({ SignContract.SideEffect.Toast("휴대폰 인증을 완료해 주세요.") })
        }
    }

    private fun passwordNext() {
        val s = viewState.value
        if (s.isPasswordReady) {
            sendEffect ({ SignContract.SideEffect.NavigateNext(SignStep.NICKNAME) })
        } else {
            val msg = when {
                s.password.isBlank() || s.passwordConfirm.isBlank() -> "비밀번호를 입력해 주세요."
                s.password != s.passwordConfirm -> "비밀번호가 일치하지 않습니다."
//                s.password.length < 8 -> "비밀번호는 8자 이상이어야 해요."
                else -> "비밀번호를 확인해 주세요."
            }
            sendEffect ({ SignContract.SideEffect.Toast(msg) })

        }
    }

    private fun onNicknameNext() {
        val s = viewState.value
        if (s.isNicknameReady) {
            sendEffect ({ SignContract.SideEffect.NavigateNext(SignStep.WELCOME) })
        } else {
            sendEffect ({ SignContract.SideEffect.Toast("닉네임을 확인해 주세요.") })
        }
    }

    private fun normalizedPhone(): String =
        viewState.value.phoneNumber.filter { it.isDigit() }

    private fun sendCode() = viewModelScope.launch {
        val phone = normalizedPhone()
        if (phone.isBlank()) {
            sendEffect ({ SignContract.SideEffect.Toast("전화번호를 입력해 주세요.") }); return@launch
        }
        updateState { copy(phoneLoading = true) }
        runCatching { sendCode(phone) }
            .onSuccess {
                println("[VM] sendCode() success")
                sendEffect ({ SignContract.SideEffect.Toast("인증번호를 전송했습니다.") })
                updateState { copy(phoneLoading = false) }
            }
            .onFailure { t ->
                println("[VM] sendCode() failed: ${t.message}")
                sendEffect ({ SignContract.SideEffect.Toast(t.message ?: "전송 실패") })
                updateState { copy(phoneLoading = false) }
            }
    }
    private fun verifyCode() = viewModelScope.launch {
        val s = viewState.value
        val phone = normalizedPhone()
        val code  = s.verificationCode.trim()
        if (phone.isBlank() || code.isBlank()) {
            sendEffect ({ SignContract.SideEffect.Toast("전화번호/인증번호를 확인해 주세요.") }); return@launch
        }
        updateState { copy(phoneLoading = true) }
        runCatching { verifyCode(phone, code) }
            .onSuccess {
                println("[VM] verifyCode() success")
                sendEffect ({ SignContract.SideEffect.Toast("인증 완료") })
                updateState { copy(phoneLoading = false, phoneVerified = true) }
            }
            .onFailure { t ->
                println("[VM] verifyCode() failed: ${t.message}")
                sendEffect ({ SignContract.SideEffect.Toast(t.message ?: "인증 실패") })
                updateState { copy(phoneLoading = false, phoneVerified = false) }
            }
    }

    private fun makeRegisterInfo(): RegisterInfo {
        val s = viewState.value

        val uidFromPhone = s.phoneNumber.trim()
        val hasUid  = uidFromPhone.isNotBlank()
        val hasUidX = !s.uidX.isNullOrBlank()
        val isSocial = s.socialProvider != null && hasUidX

        val kind = when (s.socialProvider) {
            SocialProvider.NAVER -> "NAVER"
            SocialProvider.KAKAO -> "KAKAO"
            null -> "NORMAL"
        }

        val isAgreePos   = s.required[RequiredTerm.LOCATION] == true
        val isAgreeAlert = s.optional[OptionalTerm.MARKETING] == true

        return RegisterInfo(
            uid = if (hasUid) uidFromPhone else "",
            password = if (isSocial) null else s.password,
            nickname = s.nickname.trim(),
            uidX = if (hasUid) null else s.uidX,
            kind = kind,
            height = null,
            weight = null,
            age = null,
            gender = null,
            isAgreePos = isAgreePos,
            isAgreeAlert = isAgreeAlert
        )
    }

    private fun signUp() = viewModelScope.launch {
        val info = makeRegisterInfo()
        println(
            "[SignUp] start " +
                    "kind=${info.kind}, " +
                    "uid=${info.uid?.takeIf { it.isNotBlank() } ?: "(none)"}, " +
                    "uidX=${info.uidX ?: "(none)"}, " +
                    "nickname=${info.nickname}, "
        )
        updateState { copy(signupLoading = true, signupSucceeded = null, signupError = null) }
        runCatching { register(info) }
            .onSuccess {
                println("[SignUp] SUCCESS kind=${info.kind}, uid=${info.uid ?: "(none)"}, uidX=${info.uidX ?: "(none)"}")
                updateState { copy(signupLoading = false, signupSucceeded = true, signupError = null) }
            }
            .onFailure { t ->
                println("[SignUp] FAIL   reason=${t.message ?: "unknown"}, kind=${info.kind}, uid=${info.uid ?: "(none)"}, uidX=${info.uidX ?: "(none)"}")
                updateState { copy(signupLoading = false, signupSucceeded = false, signupError = t.message ?: "회원가입 실패") }
            }
    }

    fun prepareSignup(args: SignStartArgs) {
        updateState {
            copy(
                socialProvider = args.provider,
                uidX = args.providerUserId
            )
        }
    }

    fun resetSocial() {
        updateState { copy(socialProvider = null, uidX = null) }
    }
}