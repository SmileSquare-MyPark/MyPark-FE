package com.smile.mypark.presentation.main.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions

internal class MainNavigator(
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination: Route = Route.Splash

    val currentTab: MainTab?
        @Composable get() = MainTab.find { tab ->
            currentDestination?.hasRoute(tab::class) == true
        }

    fun navigate(tab: MainTab) {
        val navOptions = navOptions {
            popUpTo(MainTabRoute.Home) { saveState = true }
            launchSingleTop = true
            restoreState = true
        }

        when (tab) {
            MainTab.HOME -> navigateHome(navOptions)
            MainTab.RESULT -> navigateResult(navOptions)
            MainTab.QR_F -> navigateQr()
            MainTab.CONTEST -> navigateContest(navOptions)
            MainTab.My -> navigateMy(navOptions)
        }
    }

    fun navigateQr(){
        navController.navigate(Route.QR)
    }

    fun navigateHomeNoStack() {
        navController.popBackStack()
        navController.navigate(MainTabRoute.Home)
    }

    fun navigateSign(){
        navController.navigate(Signup.Agreement)
    }

    fun navigatePhone(){
        navController.navigate(Signup.AuthPhone)
    }

    fun navigatePassword(){
        navController.navigate(Signup.SetPassword)
    }

    fun navigateNickname(){
        navController.navigate(Signup.SetNickname)
    }

    fun navigateWelcome(){
        navController.navigate(Signup.Welcome)
    }

    fun navigateLogin(){
        navController.navigate(Route.Login)
    }

    fun navigateAuth(){
        navController.popBackStack()
        navController.navigate(Route.Auth)
    }

    fun navigateHome(navOptions: NavOptions) {
        navController.navigate(MainTabRoute.Home, navOptions)
    }

    fun navigateMy(navOptions: NavOptions) {
        navController.navigate(MainTabRoute.My, navOptions)
    }

    fun navigateResult(navOptions: NavOptions) {
        navController.navigate(MainTabRoute.Result, navOptions)
    }

    fun navigateContest(navOptions: NavOptions) {
        navController.navigate(MainTabRoute.Contest, navOptions)
    }

    fun navigateSplash() {
        navController.navigate(Route.Splash) { popUpTo(0) { inclusive = true } }
    }

    fun navigateMap() {
        navController.navigate(Route.Map)
    }

    fun navigateContestDetail(contestId: Long) {
        navController.navigate(Route.ContestDetail(contestId))
    }

    fun navigateMyResult() {
        navController.navigate(Route.MyResult)
    }

    fun navigateChangePassword() {
        navController.navigate(Route.ChangePassword)
    }

    fun navigatePersonalSettings() {
        navController.navigate(Route.PersonalSettings)
    }

    fun navigateUp() {
        navController.popBackStack()
    }

    private fun popBackStack() {
        navController.popBackStack()
    }

    fun popBackStackIfNotHome() {
        if (!isSameCurrentDestination<MainTabRoute.Home>()) {
            popBackStack()
        }
    }

    fun popBackStackWithData( data: Map<String, Any>) {
        navController.previousBackStackEntry?.savedStateHandle?.let {
            data.forEach { (key, value) ->
                it.set(key, value)
            }
        }
        navController.popBackStack()
    }
    private inline fun <reified T : Route> isSameCurrentDestination(): Boolean {
        return navController.currentDestination?.hasRoute<T>() == true
    }

    @Composable
    fun shouldShowBottomBar(): Boolean {
        return MainTab.contains {
            currentDestination?.hasRoute(it::class) == true
        } || currentDestination?.hasRoute(Route.PersonalSettings::class) == true
    }
}


@Composable
internal fun rememberMainNavigator(
    navController: NavHostController = rememberNavController(),
): MainNavigator = remember(navController) {
    MainNavigator(navController)
}
