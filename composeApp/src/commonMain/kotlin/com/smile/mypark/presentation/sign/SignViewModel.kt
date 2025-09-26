package com.smile.mypark.presentation.sign

import androidx.lifecycle.viewModelScope
import com.smile.mypark.core.base.BaseViewModel
import com.smile.mypark.domain.repository.SignRepository
import com.smile.mypark.domain.usecase.SendVerificationCodeUseCase
import com.smile.mypark.domain.usecase.VerifyCodeUseCase
import kotlinx.coroutines.launch

class SignViewModel (
    private val sendCode: SendVerificationCodeUseCase,
    private val verifyCode: VerifyCodeUseCase,
) : BaseViewModel<SignContract.State, SignContract.SideEffect, SignContract.Event>(
    SignContract.State()
) {
    init { println("[VM_INIT] SignViewModel vm=${this.hashCode()}") }

    override fun handleEvents(event: SignContract.Event) {
        when (event) {
            SignContract.Event.ToggleAll -> toggleAll()
            is SignContract.Event.ToggleRequired -> toggleRequired(event.index)
            is SignContract.Event.ToggleOptional -> toggleOptional(event.index)
            SignContract.Event.ClickNext -> next()
            is SignContract.Event.ClickDetailRequired ->
                sendEffect ({ SignContract.SideEffect.ShowTermsDetail(SignContract.SideEffect.TermsType.REQUIRED, event.index) })
            is SignContract.Event.ClickDetailOptional ->
                sendEffect ({ SignContract.SideEffect.ShowTermsDetail(SignContract.SideEffect.TermsType.OPTIONAL, event.index) })

            is SignContract.Event.UidChanged -> updateState { copy(uid = event.uid) }
            is SignContract.Event.PwChanged -> updateState { copy(password = event.pw) }
            is SignContract.Event.NicknameChanged -> updateState { copy(nickname = event.nickname) }

            is SignContract.Event.PhoneChanged -> updateState { copy(phoneNumber = event.v) }
            is SignContract.Event.CodeChanged  -> updateState { copy(verificationCode = event.v) }
            SignContract.Event.ClickSendCode -> {
                println("[VM] ClickSendCode received")
                sendCode()
            }

            SignContract.Event.ClickVerifyCode -> verifyCode()
        }
    }

    private fun toggleAll() {
        val newValue = !viewState.value.allChecked
        updateState {
            copy(
                allChecked = newValue,
                required = List(required.size) { newValue },
                optional = List(optional.size) { newValue }
            )
        }
    }

    private fun toggleRequired(index: Int) {
        val s = viewState.value
        val req = s.required.toMutableList().apply { this[index] = !this[index] }
        val allCheckedNow = req.all { it } && s.optional.all { it }
        updateState { copy(required = req, allChecked = allCheckedNow) }
    }

    private fun toggleOptional(index: Int) {
        val s = viewState.value
        val opt = s.optional.toMutableList().apply { this[index] = !this[index] }
        val allCheckedNow = s.required.all { it } && opt.all { it }
        updateState { copy(optional = opt, allChecked = allCheckedNow) }
    }

    private fun next() {
        val s = viewState.value
        if (s.isNextEnabled) {
            sendEffect ({ SignContract.SideEffect.NavigateNext })
        } else {
            sendEffect ({ SignContract.SideEffect.Toast("필수 약관에 동의해 주세요.") })
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
                sendEffect ({ SignContract.SideEffect.Toast("인증 완료") })
                updateState { copy(phoneLoading = false, phoneVerified = true) }
            }
            .onFailure { t ->
                sendEffect ({ SignContract.SideEffect.Toast(t.message ?: "인증 실패") })
                updateState { copy(phoneLoading = false, phoneVerified = false) }
            }
    }

//    private fun signUp() = viewModelScope.launch {
//        val s = viewState.value
//        val req = SignUpRequest(
//            uid = s.uid,
//            password = s.password,
//            nickname = s.nickname,
//            consents = ConsentFlags(
//                terms = s.required[0],
//                privacy = s.required[1],
//                location = s.required[2],
//                thirdParty = s.required[3],
//                marketing = s.optional.getOrNull(0) ?: false,
//                sns = s.optional.getOrNull(1) ?: false,
//                kakaoProfile = s.optional.getOrNull(2) ?: false
//            )
//        )
//        runCatching {
//            signRepository.signUp(req)
//        }.onSuccess {
//            sendEffect { SignContract.SideEffect.NavigateNext }
//        }.onFailure {
//            sendEffect { SignContract.SideEffect.Toast("회원가입 실패: ${it.message}") }
//        }
//    }

}