package com.smile.mypark.presentation.main.component

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.smile.mypark.core.ui.theme.White
import com.smile.mypark.presentation.auth.AuthRoute
import com.smile.mypark.presentation.auth.LoginRoute
import com.smile.mypark.presentation.contest.ContestRoute
import com.smile.mypark.presentation.home.HomeRoute
import com.smile.mypark.presentation.main.SplashScreen
import com.smile.mypark.presentation.main.navigation.MainNavigator
import com.smile.mypark.presentation.main.navigation.MainTabRoute
import com.smile.mypark.presentation.main.navigation.Route
import com.smile.mypark.presentation.main.navigation.Signup
import com.smile.mypark.presentation.main.navigation.SignupGraph
import com.smile.mypark.presentation.my.MyRoute
import com.smile.mypark.presentation.qr.QrRoute
import com.smile.mypark.presentation.result.ResultRoute
import com.smile.mypark.presentation.sign.AgreementRoute
import com.smile.mypark.presentation.sign.AuthPhoneRoute
import com.smile.mypark.presentation.sign.SetNicknameRoute
import com.smile.mypark.presentation.sign.SetPasswordRoute
import com.smile.mypark.presentation.sign.SignViewModel
import com.smile.mypark.presentation.sign.WelcomeRoute
import org.koin.compose.viewmodel.koinViewModel

@Composable
internal fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    padding: PaddingValues,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(White)
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
            enterTransition = {
                slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(100))
            },
            exitTransition = {
                slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(100))
            }
        ) {
            composable<Route.Splash> {
                SplashScreen(
                    padding = padding,
                    navigateAuth = { navigator.navigateAuth() },
                    navigateHome = { navigator.navigateHomeNoStack() }
                )
            }

            composable<Route.Auth> {
                AuthRoute(
                    padding = padding,
                    navigateLogin = { navigator.navigateLogin() })
            }

            composable<Route.Login> {
                LoginRoute(
                    padding = padding,
                    navigateToHome = { navigator.navigateHomeNoStack() },
                    navigateToSignUp = { navigator.navigateSign() },
                    navigateToFindIdPw = { }
                )
            }

            navigation<SignupGraph>(startDestination = Signup.Agreement) {

                composable<Signup.Agreement> { backStackEntry ->
                    val signupOwner = remember(backStackEntry) {
                        navigator.navController.getBackStackEntry(SignupGraph)
                    }
                    val viewModel: SignViewModel = koinViewModel(viewModelStoreOwner = signupOwner)

                    AgreementRoute(
                        padding = padding,
                        navigateNext = { navigator.navigatePhone() },
                        viewModel = viewModel
                    )
                }

                composable<Signup.AuthPhone> { backStackEntry ->
                    val signupOwner = remember(backStackEntry) {
                        navigator.navController.getBackStackEntry(SignupGraph)
                    }
                    val viewModel: SignViewModel = koinViewModel(viewModelStoreOwner = signupOwner)

                    AuthPhoneRoute(
                        padding = padding,
                        navigateNext = { navigator.navigatePassword() },
                        viewModel = viewModel
                    )
                }

                composable<Signup.SetPassword> { backStackEntry ->
                    val signupOwner = remember(backStackEntry) {
                        navigator.navController.getBackStackEntry(SignupGraph)
                    }
                    val viewModel: SignViewModel = koinViewModel(viewModelStoreOwner = signupOwner)

                    SetPasswordRoute(
                        padding = padding,
                        onClick = { navigator.navigateNickname() },
                        viewModel = viewModel
                    )
                }

                composable<Signup.SetNickname> { backStackEntry ->
                    val signupOwner = remember(backStackEntry) {
                        navigator.navController.getBackStackEntry(SignupGraph)
                    }
                    val viewModel: SignViewModel = koinViewModel(viewModelStoreOwner = signupOwner)

                    SetNicknameRoute(
                        padding = padding,
                        onClick = { navigator.navigateWelcome() },
                        viewModel = viewModel
                    )
                }

                composable<Signup.Welcome> { backStackEntry ->
                    val signupOwner = remember(backStackEntry) {
                        navigator.navController.getBackStackEntry(SignupGraph)
                    }
                    val viewModel: SignViewModel = koinViewModel(viewModelStoreOwner = signupOwner)

                    WelcomeRoute(
                        padding = padding,
                        onClick = { navigator.navigateHomeNoStack() },
                        viewModel = viewModel
                    )
                }
            }

            composable<MainTabRoute.Home> {
                HomeRoute(padding = padding)
            }

            composable<MainTabRoute.Result> {
                ResultRoute(padding = padding)
            }

            composable<MainTabRoute.Contest> {
                ContestRoute(padding = padding)
            }

            composable<MainTabRoute.My> {
                MyRoute(padding = padding)
            }

            composable<Route.QR> {
                QrRoute(padding = padding)
            }
        }
    }
}