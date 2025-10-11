package com.smile.mypark.presentation.auth

import androidx.lifecycle.viewModelScope
import com.smile.mypark.core.base.BaseViewModel
import com.smile.mypark.domain.error.NotMemberException
import com.smile.mypark.domain.model.SignStartArgs
import com.smile.mypark.domain.repository.KakaoLoginRepository
import com.smile.mypark.domain.repository.NaverLoginRepository
import com.smile.mypark.domain.usecase.LoginUseCase
import com.smile.mypark.presentation.sign.SocialProvider
import kotlinx.coroutines.launch

class AuthViewModel(
    private val loginUseCase: LoginUseCase,
    private val kakaoGateway: KakaoLoginGateway,
    private val naverGateway: NaverLoginGateway,
    private val naverLoginRepository: NaverLoginRepository,
    private val kakaoLoginRepository: KakaoLoginRepository
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
            AuthContract.AuthEvent.ClickSignUp -> sendEffect ({ AuthContract.AuthSideEffect.NavigateSignup() })
            AuthContract.AuthEvent.ClickFindIdPw -> sendEffect ({ AuthContract.AuthSideEffect.NavigateFindIdPw })
            AuthContract.AuthEvent.ClickKakaoLogin -> loginKakao()
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
            .onSuccess { t ->
                println("[Login] success id=${s.id} success=${t.accessToken}")
                updateState { copy(loading = false, pw = "") }
                sendEffect({ AuthContract.AuthSideEffect.NavigateHome })
            }
            .onFailure { t ->
                println("[Login] fail id=${s.id} error=${t.message}")
                updateState { copy(loading = false, error = t.message) }
                sendEffect({ AuthContract.AuthSideEffect.Toast(t.message ?: "로그인 실패") })
            }
    }

    private fun loginKakao() = viewModelScope.launch {
        updateState { copy(isLoading = true, error = null) }

        var lastKakaoId: String? = null

        runCatching {
            // 1) 카카오 SDK 로그인 → 액세스 토큰 획득
            val accessToken = kakaoGateway.login()

            // 2) 액세스 토큰으로 카카오 유저 ID 조회
            val kakaoId = kakaoLoginRepository.fetchUserId(accessToken).trim()
            lastKakaoId = kakaoId
            println("[Kakao] fetched id=$kakaoId")

            // 3) uid 자리에 kakaoId, password는 빈 문자열로 로그인 API 호출
            println("[Kakao] about to call loginUseCase uid=$kakaoId")
            val tokens = loginUseCase(kakaoId, "")
            println("[Kakao] loginUseCase OK uid=$kakaoId")

            tokens
        }
            .onSuccess {
                updateState { copy(isLoading = false, token = null) }
                sendEffect ({ AuthContract.AuthSideEffect.NavigateHome })
            }
            .onFailure { e ->
                updateState { copy(isLoading = false, error = e.message) }
                when (e) {
                    is NotMemberException -> {
                        val kid = lastKakaoId
                        if (kid.isNullOrBlank()) {
                            println("[Auth] NotMember(Kakao) but kakaoId missing")
                            sendEffect ({ AuthContract.AuthSideEffect.Toast("회원가입을 다시 시도해 주세요.") })
                            return@onFailure
                        }
                        val args = SignStartArgs(
                            provider = SocialProvider.KAKAO,
                            providerUserId = kid
                        )
                        println("[Auth] NotMember(Kakao) → navigate signup")
                        sendEffect ({ AuthContract.AuthSideEffect.NavigateSignup(args) })
                    }
                    else -> {
                        println("[Kakao] loginUseCase FAILED: ${e.message}")
                        sendEffect ({ AuthContract.AuthSideEffect.Toast(e.message ?: "카카오 로그인 실패") })
                    }
                }
            }
    }

    private fun loginNaver() = viewModelScope.launch {
        updateState { copy(isLoading = true, error = null) }

        var lastNaverId: String? = null

        runCatching {
            val accessToken = naverGateway.login()
            val naverId = naverLoginRepository.fetchUserId(accessToken)
            lastNaverId = naverId

            println("[Naver] fetched id=$naverId")

            println("[Naver] about to call loginUseCase id=$naverId")
            val tokens = loginUseCase(naverId, "")
            println("[Naver] loginUseCase OK id=$naverId")   // 성공시에만 찍힘

            tokens
        }
            .onSuccess {
                updateState { copy(isLoading = false, token = null) }
                sendEffect ({ AuthContract.AuthSideEffect.NavigateHome })
            }
            .onFailure { e ->
                updateState { copy(isLoading = false, error = e.message) }
                when (e) {
                    is NotMemberException -> {
                        val nid = lastNaverId
                        if (nid.isNullOrBlank()) {
                            println("[Auth] NotMember but naverId missing")
                            sendEffect ({ AuthContract.AuthSideEffect.Toast("회원가입을 다시 시도해 주세요.") })
                            return@onFailure
                        }
                        val args = SignStartArgs(
                            provider = SocialProvider.NAVER,
                            providerUserId = nid,
                        )
                        println("[Auth] NotMember(Naver) → navigate signup")
                        sendEffect ({ AuthContract.AuthSideEffect.NavigateSignup(args) })
                    }
                    else -> {
                        println("[Naver] loginUseCase FAILED: ${e.message}")
                        sendEffect ({ AuthContract.AuthSideEffect.Toast(e.message ?: "네이버 로그인 실패") })
                    }
                }
            }
    }

}