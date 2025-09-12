package com.smile.mypark.presentation.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.smile.mypark.presentation.auth.AuthRoute
import com.smile.mypark.presentation.contest.ContestRoute
import com.smile.mypark.presentation.home.HomeRoute
import com.smile.mypark.presentation.main.SplashScreen
import com.smile.mypark.presentation.main.navigation.MainNavigator
import com.smile.mypark.presentation.main.navigation.MainTabRoute
import com.smile.mypark.presentation.main.navigation.Route
import com.smile.mypark.presentation.my.MyRoute
import com.smile.mypark.presentation.qr.QrRoute
import com.smile.mypark.presentation.result.ResultRoute
import com.smile.mypark.presentation.sign.SignRoute

@Composable
internal fun MainNavHost(
    modifier: Modifier = Modifier,
    navigator: MainNavigator,
    padding: PaddingValues,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surfaceDim)
    ) {
        NavHost(
            navController = navigator.navController,
            startDestination = navigator.startDestination,
        ) {
            composable<Route.Splash> {
                SplashScreen(
                    padding = padding,
                    navigateAuth = { navigator.navigateAuth() },
                    navigateHome = { navigator.navigateHomeNoStack() }
                )
            }

            composable<Route.Login> {
                AuthRoute(
                    padding = padding,
                    onClickSign = { navigator.navigateSign() })
            }

            composable<Route.Signup> {
                SignRoute(padding = padding)
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
