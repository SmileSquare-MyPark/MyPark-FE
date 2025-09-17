package com.smile.mypark.presentation.auth.component

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.ic_mypark_logo
import org.jetbrains.compose.resources.painterResource

@Composable
fun MyParkLogo(
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(Res.drawable.ic_mypark_logo),
        contentDescription = null,
        modifier = modifier
    )
}
