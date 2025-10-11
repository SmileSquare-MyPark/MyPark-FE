package com.smile.mypark.domain.model

import com.smile.mypark.presentation.sign.SocialProvider

data class RegisterInfo(
    val uid: String? = null,
    val password: String? = null,
    val nickname: String,
    val uidX: String? = null,
    val kind: String? = null,
    val height: Int? = null,
    val weight: Int? = null,
    val age: Int? = null,
    val gender: String? = null,
    val isAgreePos: Boolean? = null,
    val isAgreeAlert: Boolean? = null
)

data class SignStartArgs(
    val provider: SocialProvider,
    val providerUserId: String,
    val nickname: String? = null,
    val email: String? = null,
    val avatarUrl: String? = null
)