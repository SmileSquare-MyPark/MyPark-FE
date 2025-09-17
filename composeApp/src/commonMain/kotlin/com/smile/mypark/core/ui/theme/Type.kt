package com.smile.mypark.core.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import mypark.composeapp.generated.resources.NanumSquareRoundR
import mypark.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.Font

@Composable
fun nanumSquareFamily(): FontFamily = FontFamily(
    Font(Res.font.NanumSquareRoundR, weight = FontWeight.Normal)
)


@Composable
fun nanumBaseStyle(): TextStyle = TextStyle(
    fontFamily = nanumSquareFamily(),
    fontWeight = FontWeight.Normal
)

@Composable
fun nanumTypography(): Typography {
    val nanum = nanumBaseStyle()

    return Typography(
        bodyLarge = nanum.copy(
            fontWeight = FontWeight(600),
            color = Black
        ),
        bodyMedium = nanum.copy(
            fontWeight = FontWeight(500),
            color = Black
        ),
        bodySmall = nanum.copy(
            fontWeight = FontWeight(400),
            color = Black
        ),


//        displayLargeR = Nanum.copy(
//            fontSize = 57.sp,
//            lineHeight = 64.sp,
//            letterSpacing = (-0.25).sp,
//        ),
//        displayMediumR = Nanum.copy(
//            fontSize = 45.sp,
//            lineHeight = 52.sp,
//        ),
//        displaySmallR = Nanum.copy(
//            fontSize = 36.sp,
//            lineHeight = 44.sp,
//        ),
//        headlineLargeEB = Nanum.copy(
//            fontSize = 32.sp,
//            lineHeight = 40.sp,
//            fontWeight = FontWeight.ExtraBold
//        ),
//        headlineLargeSB = Nanum.copy(
//            fontSize = 32.sp,
//            lineHeight = 40.sp,
//            fontWeight = FontWeight.SemiBold
//        ),
//        headlineLargeR = Nanum.copy(
//            fontSize = 32.sp,
//            lineHeight = 40.sp
//        ),
//        headlineMediumB = Nanum.copy(
//            fontSize = 28.sp,
//            lineHeight = 36.sp,
//            fontWeight = FontWeight.Bold
//        ),
//        headlineMediumM = Nanum.copy(
//            fontSize = 28.sp,
//            lineHeight = 36.sp,
//            fontWeight = FontWeight.Medium
//        ),
//        headlineMediumR = Nanum.copy(
//            fontSize = 28.sp,
//            lineHeight = 36.sp
//        ),
//        headlineSmallBL = Nanum.copy(
//            fontSize = 24.sp,
//            lineHeight = 32.sp,
//            fontWeight = FontWeight.Black,
//            letterSpacing = (-0.2).sp
//        ),
//        headlineSmallM = Nanum.copy(
//            fontSize = 24.sp,
//            lineHeight = 32.sp,
//            fontWeight = FontWeight.Medium
//        ),
//        headlineSmallR = Nanum.copy(
//            fontSize = 24.sp,
//            lineHeight = 32.sp
//        ),
//        titleLargeBL = Nanum.copy(
//            fontSize = 22.sp,
//            lineHeight = 28.sp,
//            fontWeight = FontWeight.Black
//        ),
//        titleLargeB = Nanum.copy(
//            fontSize = 22.sp,
//            lineHeight = 28.sp,
//            fontWeight = FontWeight.Bold
//        ),
//        titleLargeM = Nanum.copy(
//            fontSize = 22.sp,
//            lineHeight = 28.sp,
//            fontWeight = FontWeight.Medium
//        ),
//        titleLargeR = Nanum.copy(
//            fontSize = 22.sp,
//            lineHeight = 28.sp
//        ),
//        titleMediumBL = Nanum.copy(
//            fontSize = 16.sp,
//            lineHeight = 24.sp,
//            fontWeight = FontWeight.Black
//        ),
//        titleMediumB = Nanum.copy(
//            fontSize = 16.sp,
//            lineHeight = 24.sp,
//            fontWeight = FontWeight.Bold
//        ),
//        titleMediumR = Nanum.copy(
//            fontSize = 16.sp,
//            lineHeight = 24.sp
//        ),
//        titleSmallB = Nanum.copy(
//            fontSize = 14.sp,
//            lineHeight = 20.sp,
//            fontWeight = FontWeight.Bold,
//            letterSpacing = 0.25.sp
//        ),
//        titleSmallM = Nanum.copy(
//            fontSize = 14.sp,
//            lineHeight = 20.sp,
//            fontWeight = FontWeight.Medium,
//            letterSpacing = 0.25.sp
//        ),
//        titleSmallM140 = Nanum.copy(
//            fontSize = 14.sp,
//            lineHeight = 19.6.sp,
//            fontWeight = FontWeight.Medium,
//            letterSpacing = (-0.2).sp
//        ),
//        titleSmallR140 = Nanum.copy(
//            fontSize = 14.sp,
//            lineHeight = 19.6.sp,
//            letterSpacing = (-0.2).sp
//        ),
//        titleSmallR = Nanum.copy(
//            fontSize = 14.sp,
//            lineHeight = 20.sp
//        ),
//        labelLargeM = Nanum.copy(
//            fontSize = 12.sp,
//            lineHeight = 16.sp,
//            fontWeight = FontWeight.Medium
//        ),
//        labelMediumR= Nanum.copy(
//            fontSize = 12.sp,
//            lineHeight = 16.sp
//        ),
//        labelSmallM = Nanum.copy(
//            fontSize = 11.sp,
//            lineHeight = 16.sp,
//            fontWeight = FontWeight.Medium,
//            letterSpacing = (-0.2).sp
//        ),
//        bodyLargeR  = Nanum.copy(
//            fontSize = 16.sp,
//            lineHeight = 24.sp,
//            letterSpacing = 0.5.sp
//        ),
//        bodyMediumR = Nanum.copy(
//            fontSize = 14.sp,
//            lineHeight = 20.sp,
//            letterSpacing = 0.25.sp
//        ),
//        bodySmallR  = Nanum.copy(
//            fontSize = 12.sp,
//            lineHeight = 16.sp
//        ),
    )
}

@Immutable
data class BaseTypography(
    val displayLargeR: TextStyle,
    val displayMediumR: TextStyle,
    val displaySmallR: TextStyle,

    val headlineLargeEB: TextStyle,
    val headlineLargeSB: TextStyle,
    val headlineLargeR: TextStyle,
    val headlineMediumB: TextStyle,
    val headlineMediumM: TextStyle,
    val headlineMediumR: TextStyle,
    val headlineSmallBL: TextStyle,
    val headlineSmallM: TextStyle,
    val headlineSmallR: TextStyle,

    val titleLargeBL: TextStyle,
    val titleLargeB: TextStyle,
    val titleLargeM: TextStyle,
    val titleLargeR: TextStyle,
    val titleMediumBL: TextStyle,
    val titleMediumB: TextStyle,
    val titleMediumR: TextStyle,
    val titleSmallB: TextStyle,
    val titleSmallM: TextStyle,
    val titleSmallM140: TextStyle,
    val titleSmallR: TextStyle,
    val titleSmallR140: TextStyle,

    val labelLargeM: TextStyle,
    val labelMediumR: TextStyle,
    val labelSmallM: TextStyle,

    val bodyLargeR: TextStyle,
    val bodyMediumR: TextStyle,
    val bodySmallR: TextStyle,
)