package com.smile.mypark.presentation.main.navigation

import kotlinx.serialization.Serializable

sealed interface Route {
    @Serializable
    data object Splash : Route

    @Serializable
    data object Auth : Route

    @Serializable
    data object Login : Route

//    @Serializable
//    data object Signup : Route

    @Serializable
    data object QR : Route

    @Serializable
    data class Setting(val id: String) : Route
}

sealed interface MainTabRoute : Route {
    @Serializable
    data object Home : MainTabRoute

    @Serializable
    data object Result : MainTabRoute

    @Serializable
    data object Contest : MainTabRoute

    @Serializable
    data object My : MainTabRoute
}

@Serializable
data object SignupGraph : Route

@Serializable
sealed interface Signup : Route {
    @Serializable
    data object Agreement : Signup

    @Serializable
    data object AuthPhone : Signup

    @Serializable
    data object SetPassword : Signup

    @Serializable
    data object SetNickname : Signup

    @Serializable
    data object Welcome : Signup
}