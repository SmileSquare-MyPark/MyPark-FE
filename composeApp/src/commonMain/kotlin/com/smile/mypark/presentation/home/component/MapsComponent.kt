package com.smile.mypark.presentation.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.input.key.Key.Companion.R
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.smile.mypark.core.ext.noRippleSingleClickable
import com.smile.mypark.core.ext.toFixedSp
import com.smile.mypark.core.ui.theme.CharcoalGray
import com.smile.mypark.core.ui.theme.Gray160
import com.smile.mypark.core.ui.theme.MediumLightGray
import com.smile.mypark.core.ui.theme.Primary
import com.smile.mypark.core.ui.theme.VeryLightGray242
import com.smile.mypark.core.ui.theme.White
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.call
import mypark.composeapp.generated.resources.find_direction
import mypark.composeapp.generated.resources.find_store
import mypark.composeapp.generated.resources.ic_back
import mypark.composeapp.generated.resources.ic_directions
import mypark.composeapp.generated.resources.ic_heart_black_line
import mypark.composeapp.generated.resources.ic_heart_white
import mypark.composeapp.generated.resources.ic_list
import mypark.composeapp.generated.resources.ic_location_black
import mypark.composeapp.generated.resources.ic_location_gradation
import mypark.composeapp.generated.resources.ic_menu_green
import mypark.composeapp.generated.resources.ic_phone
import mypark.composeapp.generated.resources.ic_search
import mypark.composeapp.generated.resources.list
import mypark.composeapp.generated.resources.store
import mypark.composeapp.generated.resources.wish_list
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Immutable
data class StoreUi(
    val name: String,
    val slots: Int,
    val distanceText: String,
    val address: String,
    val rating: Float,
    val phone: String,
    val lat: Double,
    val lng: Double
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapOverlayUI(
    modifier: Modifier = Modifier,
    onBack: () -> Unit = {},
    onMenu: () -> Unit = {},
    keyword: TextFieldValue,
    onKeywordChange: (TextFieldValue) -> Unit,
    onSearch: (String) -> Unit = {},
    onAddList: () -> Unit = {},
    onOpenList: () -> Unit = {},
    onLocation: () -> Unit = {},
    selected: StoreUi?,
    onCall: (StoreUi) -> Unit = {},
    onRoute: (StoreUi) -> Unit = {},
) {
    Box(modifier.fillMaxSize()) {
        Column(
            Modifier
                .fillMaxWidth()
                .align(Alignment.TopCenter)
                .background(Transparent)

        ) {
            MapTopBar(
                keyword = keyword,
                onKeywordChange = onKeywordChange,
                onBack = onBack,
                onMenu = onMenu
            )
        }

        Box(Modifier.fillMaxSize()) {
            StoreHeartButton(
                onAddList = onAddList,
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(bottom = 250.dp, start = 15.dp)
            )
        }

        Box(Modifier.fillMaxSize()) {
            StoreListButton(
                onOpenList = onOpenList,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 250.dp)
            )
        }

        Box(Modifier.fillMaxSize()) {
            MapLocationButton(
                onLocation = onLocation,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(bottom = 250.dp, end = 15.dp)
            )
        }

        if (selected != null) {
            StoreBottomCard(
                store = selected,
                onCall = onCall,
                onRoute = onRoute,
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 30.dp, start = 15.dp, end = 15.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MapTopBar(
    keyword: TextFieldValue,
    onKeywordChange: (TextFieldValue) -> Unit,
    onBack: () -> Unit = {},
    onMenu: () -> Unit = {},
    onSearch: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(White)
                .padding(top = 65.dp, bottom = 5.dp)
        ) {
            Text(
                text = stringResource(Res.string.store),
                style = MaterialTheme.typography.headlineLarge.copy(
                    fontSize = 25.toFixedSp(),
                    lineHeight = 31.toFixedSp()
                ),
                color = Primary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
            )

            IconButton(
                onClick = onMenu,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 11.dp)
                    .sizeIn(minWidth = 24.dp, minHeight = 48.dp)
                    .size(width = 24.dp, height = 19.dp)
            ) {
                Icon(
                    painter = painterResource(Res.drawable.ic_menu_green),
                    contentDescription = "메뉴",
                    tint = Color.Unspecified
                )
            }
        }

        Surface(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp),
            color = White
        ){
            TextField(
                value = keyword,
                onValueChange = onKeywordChange,
                placeholder = { },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 49.dp)
                    .padding(bottom = 8.dp, start = 11.dp, end = 11.dp),
                shape = RoundedCornerShape(10.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = VeryLightGray242,
                    unfocusedContainerColor = VeryLightGray242,
                    disabledContainerColor = VeryLightGray242,
                    errorContainerColor = VeryLightGray242,

                    focusedIndicatorColor = Transparent,
                    unfocusedIndicatorColor = Transparent,
                    disabledIndicatorColor = Transparent,
                    errorIndicatorColor = Transparent,
                ),
                leadingIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            painterResource(Res.drawable.ic_back),
                            contentDescription = null,
                            modifier = Modifier
                                .size(24.dp)
                                .padding(end = 10.dp),
                            tint = Color(0xFFc5c5c5)
                        )
                    }
                },
                trailingIcon = {
                    IconButton(onClick = { onSearch(keyword.text) }) {
                        Image(
                            painterResource(Res.drawable.ic_search),
                            null,
                            modifier = Modifier
                                .size(width = 23.dp, height = 23.dp)
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = {
                    onSearch(keyword.text)
                })
            )
        }

    }
}

@Composable
fun StoreBottomCard(
    store: StoreUi,
    onCall: (StoreUi) -> Unit,
    onRoute: (StoreUi) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        tonalElevation = 6.dp,
        shadowElevation = 12.dp,
        shape = RoundedCornerShape(8.dp),
        color = White
    ) {
        Column(Modifier.fillMaxWidth()) {

            Column(Modifier.padding(horizontal = 16.dp, vertical = 14.dp)) {

                Text(
                    text = store.name,
                    style = MaterialTheme.typography.headlineLarge.copy(
                        fontSize = 20.toFixedSp(),
                        lineHeight = 28.toFixedSp()
                    ),
                    color = CharcoalGray
                )

                Spacer(Modifier.height(5.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(White)
                            .border(1.dp, Gray160, RoundedCornerShape(10.dp))
                    )
                    Column(Modifier.weight(1f)) {
                        Text(
                            text = "${store.slots}대",
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 15.toFixedSp(),
                                lineHeight = 22.toFixedSp(),
                            ),
                            color = Color(0xFF888888)
                        )

                        Spacer(Modifier.height(4.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_location_gradation),
                                contentDescription = null,
                                tint = Color.Unspecified,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(Modifier.width(4.dp))
                            Text(
                                text = store.distanceText,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = 15.toFixedSp(),
                                    lineHeight = 20.toFixedSp()
                                ),
                                color = Color(0xFF888888)
                            )
                            Spacer(Modifier.width(8.dp))
                            Text(" | ", color = Color(0xFF888888))
                            Spacer(Modifier.width(8.dp))
                            Text(
                                text = store.address,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontSize = 10.toFixedSp(),
                                    lineHeight = 12.toFixedSp(),
                                ),
                                color = MediumLightGray,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }

                        Spacer(Modifier.height(6.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            repeat(5) { idx ->
                                Icon(
                                    painter = painterResource(Res.drawable.ic_heart_white),
                                    contentDescription = null,
                                    tint = Primary,
                                    modifier = Modifier
                                        .size(18.dp)
                                        .padding(end = 2.dp)
                                )
                            }
                        }
                    }
                }
            }

            HorizontalDivider(Modifier.padding(horizontal = 17.dp), 1.dp, MediumLightGray)

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .padding(horizontal = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextButton(
                    onClick = { onCall(store) },
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_phone),
                            contentDescription = "전화",
                            tint = Primary,
                            modifier = Modifier.size(15.dp)
                        )
                        Spacer(Modifier.width(5.dp))
                        Text(
                            text = stringResource(Res.string.call),
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 15.toFixedSp()
                            ),
                            color = Color(0xFF888888)
                        )
                    }
                }

                Box(
                    Modifier
                        .width(1.dp)
                        .height(26.dp)
                        .background(MediumLightGray)
                )

                TextButton(
                    onClick = { onRoute(store) },
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(0.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .size(24.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(Res.drawable.ic_directions),
                                contentDescription = "길찾기",
                                tint = Primary,
                                modifier = Modifier.size(23.dp)
                            )
                        }
                        Spacer(Modifier.width(8.dp))
                        Text(
                            text = stringResource(Res.string.find_direction),
                            style = MaterialTheme.typography.bodyLarge.copy(
                                fontSize = 15.toFixedSp()
                            ),
                            color = Color(0xFF888888)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun StoreListButton(
    onOpenList: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onOpenList,
        shape = RoundedCornerShape(13.5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Primary,
            contentColor = Color.White
        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
        modifier = modifier
            .height(30.dp)
            .defaultMinSize(minWidth = 64.dp, minHeight = 30.dp)
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_list),
            contentDescription = "목록",
            modifier = Modifier.size(16.dp)
        )
        Spacer(Modifier.width(6.dp))
        Text(
            text = stringResource(Res.string.list),
            style = MaterialTheme.typography.labelLarge.copy(
                fontSize = 15.toFixedSp(),
                lineHeight = 19.toFixedSp()
            ),
            maxLines = 1,
            modifier = Modifier
                .padding(bottom = 3.dp),
        )
    }
}

@Composable
fun StoreHeartButton(
    onAddList: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onAddList,
        shape = RoundedCornerShape(13.5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = White
        ),
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
        modifier = modifier
            .height(30.dp)
            .defaultMinSize(minWidth = 64.dp, minHeight = 30.dp)
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_heart_black_line),
            contentDescription = "목록",
            tint = Color.Unspecified,
            modifier = Modifier.size(16.dp)
        )
        Spacer(Modifier.width(5.dp))
        Text(
            text = stringResource(Res.string.wish_list),
            style = MaterialTheme.typography.labelLarge.copy(
                fontSize = 15.toFixedSp(),
                lineHeight = 19.toFixedSp()
            ),
            color = Primary,
            maxLines = 1,
            modifier = Modifier
                .padding(bottom = 3.dp),
        )
    }
}

@Composable
fun MapLocationButton(
    onLocation: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onLocation,
        shape = RoundedCornerShape(13.5.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = White
        ),
        contentPadding = PaddingValues(horizontal = 5.dp, vertical = 4.dp),
        modifier = modifier
            .height(30.dp)
            .defaultMinSize(minWidth = 30.dp, minHeight = 30.dp)
    ) {
        Icon(
            painter = painterResource(Res.drawable.ic_location_black),
            contentDescription = "위치",
            tint = Color.Unspecified,
            modifier = Modifier.size(17.dp)
        )
    }
}
@Preview
@Composable
fun Preview_MapOverlayUI_Default() {
    MaterialTheme {
        var keyword by remember { mutableStateOf(TextFieldValue("가산")) }
        MapOverlayUI(
            keyword = keyword,
            onKeywordChange = { keyword = it },
            onSearch = {},
            onBack = {},
            onMenu = {},
            onOpenList = {},
            selected = StoreUi(
                name = "마이파크 가산점",
                slots = 14,
                distanceText = "645m",
                address = "금천구 가산동",
                rating = 4.0f,
                phone = "02-123-4567",
                lat = 37.4789, lng = 126.8811
            ),
            onCall = {},
            onRoute = {}
        )
    }
}

@Preview
@Composable
fun Preview_StoreBottomCard() {
    MaterialTheme {
        StoreBottomCard(
            store = StoreUi(
                name = "마이파크 개포점",
                slots = 7,
                distanceText = "1.2km",
                address = "강남구 개포동",
                rating = 5f,
                phone = "02-555-7777",
                lat = 37.48, lng = 127.06
            ),
            onCall = {},
            onRoute = {},
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun Preview_MapTopBarOnly() {
    MaterialTheme {
        var keyword by remember { mutableStateOf(TextFieldValue("")) }
        MapTopBar(
            keyword = keyword,
            onKeywordChange = { keyword = it },
            onBack = {},
            onMenu = {},
            onSearch = {}
        )
    }
}