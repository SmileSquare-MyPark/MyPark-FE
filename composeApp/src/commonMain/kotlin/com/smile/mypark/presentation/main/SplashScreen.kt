package com.smile.mypark.presentation.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SplashScreen(
    padding: PaddingValues,
    navigateAuth: () -> Unit,
    navigateHome: () -> Unit,
) {
    Column(
        Modifier.padding(padding)
    ) {
        Spacer(Modifier.height(10.dp))
        Text(text = "Auth" , fontSize = 32.sp, modifier = Modifier.clickable(onClick = navigateAuth))
        Spacer(Modifier.height(10.dp))
        Text(text = "Home" , fontSize = 32.sp, modifier = Modifier.clickable(onClick = navigateHome))
    }

}