package com.smile.mypark.presentation.main.navigation

import androidx.compose.runtime.Composable
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.ic_blank
import mypark.composeapp.generated.resources.ic_main_qr
import mypark.composeapp.generated.resources.ic_test
import org.jetbrains.compose.resources.DrawableResource

internal enum class MainTab(
    val iconResId: DrawableResource,
    internal val contentDescription: String,
    val route: MainTabRoute,
) {
    HOME(
        iconResId = Res.drawable.ic_test,
        contentDescription = "홈",
        MainTabRoute.Home
    ),

    RESULT(
        iconResId = Res.drawable.ic_test,
        contentDescription = "결과",
        MainTabRoute.Result
    ),

    QR_F(
        iconResId = Res.drawable.ic_main_qr,
        contentDescription = "qr",
        MainTabRoute.Result
    ),

    CONTEST(
        iconResId =Res.drawable.ic_test,
        contentDescription = "대회",
        MainTabRoute.Contest
    ),

    My(
        iconResId = Res.drawable.ic_test,
        contentDescription = "북마크",
        MainTabRoute.My,
    );

    companion object Companion {
        @Composable
        fun find(predicate: @Composable (MainTabRoute) -> Boolean): MainTab? {
            return entries.find { predicate(it.route) }
        }

        @Composable
        fun contains(predicate: @Composable (Route) -> Boolean): Boolean {
            return entries.map { it.route }.any { predicate(it) }
        }
    }
}