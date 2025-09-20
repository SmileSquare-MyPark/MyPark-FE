package com.smile.mypark.presentation.sign.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.smile.mypark.core.ext.toFixedSp
import com.smile.mypark.core.ui.component.CustomRadioButton
import com.smile.mypark.core.ui.theme.Gray
import com.smile.mypark.core.ui.theme.LightGray179
import com.smile.mypark.core.ui.theme.NeutralGray
import com.smile.mypark.core.ui.theme.White
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.ic_radio_off
import mypark.composeapp.generated.resources.ic_radio_on
import mypark.composeapp.generated.resources.required
import mypark.composeapp.generated.resources.see_more
import mypark.composeapp.generated.resources.terms_location
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun TermsList(
    title: String,
    selected: Boolean,
    onToggle: () -> Unit,
    onClickDetail: () -> Unit,
    showSeeMore: Boolean = true,
    modifier: Modifier = Modifier
){
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onToggle() }
            .padding(vertical= 6.5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomRadioButton(
            selected = selected,
            onClick = onToggle,
            modifier = Modifier.size(25.dp)
        )

        Spacer(Modifier.width(10.dp))

        Box(
            modifier = Modifier
                .border(
                    width = 0.5.dp,
                    color = LightGray179,
                    shape = RoundedCornerShape(8.5.dp)
                )
                .background(
                    color = White,
                    shape = RoundedCornerShape(8.5.dp)
                )
                .padding(horizontal = 6.dp, vertical = 2.dp)
        ) {
            Text(
                text = stringResource(Res.string.required),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.toFixedSp(),
                    lineHeight = 14.toFixedSp(),
                    color = LightGray179
                )
            )
        }

        Spacer(Modifier.width(8.dp))

        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontSize = 16.toFixedSp(),
                lineHeight = 18.toFixedSp(),
                color = NeutralGray
            )
        )

        Spacer(Modifier.weight(1f))

        if (showSeeMore) {
            Text(
                text = stringResource(Res.string.see_more),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontSize = 10.toFixedSp(),
                    lineHeight = 11.toFixedSp(),
                    color = LightGray179,
                    textDecoration = TextDecoration.Underline
                ),
                modifier = Modifier.clickable { onClickDetail() }
            )
        }
    }

}

@Preview
@Composable
private fun TermListPreview() {
    TermsList(
        title = stringResource(Res.string.terms_location),
        selected = true,
        onToggle = {},
        onClickDetail = {}
    )
}