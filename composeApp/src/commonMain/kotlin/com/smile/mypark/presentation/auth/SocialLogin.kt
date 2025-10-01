package com.smile.mypark.presentation.auth

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

interface KakaoLoginGateway {
    suspend fun login(): String
}

interface NaverLoginGateway {
    suspend fun login(): String
}


object AuthKakaoResultBridge {
    private val _events = MutableSharedFlow<Result<String>>(extraBufferCapacity = 1)
    val events = _events.asSharedFlow()


    @Suppress("unused")
    fun kakaoLoginSuccess(token: String) {
        _events.tryEmit(Result.success(token))
    }

    @Suppress("unused")
    fun kakaoLoginFailure(message: String?) {
        _events.tryEmit(Result.failure(IllegalStateException(message ?: "Kakao login cancelled")))
    }
}

object AuthNaverResultBridge {
    private val _events = MutableSharedFlow<Result<String>>(extraBufferCapacity = 1)
    val events = _events.asSharedFlow()

    @Suppress("unused")
    fun naverLoginSuccess(token: String) { _events.tryEmit(Result.success(token)) }

    @Suppress("unused")
    fun naverLoginFailure(message: String?) {
        _events.tryEmit(Result.failure(IllegalStateException(message ?: "Naver login cancelled")))
    }
}