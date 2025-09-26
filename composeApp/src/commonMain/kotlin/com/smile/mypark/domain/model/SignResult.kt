package com.smile.mypark.domain.model

data class RegisterInfo(
    val uid: String? = null,
    val password: String? = null,
    val nickname: String,
    val uidX: Int? = null,
    val kind: String? = null,
    val height: Int? = null,
    val weight: Int? = null,
    val age: Int? = null,
    val gender: String? = null,
    val isAgreePos: Boolean? = null,
    val isAgreeAlert: Boolean? = null
)
