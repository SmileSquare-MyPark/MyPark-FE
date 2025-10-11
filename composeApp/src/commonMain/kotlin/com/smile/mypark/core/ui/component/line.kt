package com.smile.mypark.core.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.smile.mypark.core.ui.theme.Gray30

@Composable
fun HorizontalLine(){
    Box(
        Modifier.fillMaxWidth().height(2.dp).background(Gray30, shape = RoundedCornerShape(50))
    )
}

@Composable
fun VerticalLine(){
    Box(
        Modifier.fillMaxHeight().width(2.dp).background(Gray30, shape = RoundedCornerShape(50))
    )
}