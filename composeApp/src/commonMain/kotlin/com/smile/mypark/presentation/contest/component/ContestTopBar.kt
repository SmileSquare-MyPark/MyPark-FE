import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import com.smile.mypark.core.ext.toFixedSp
import com.smile.mypark.core.ui.theme.Black
import com.smile.mypark.core.ui.theme.Gray153
import com.smile.mypark.core.ui.theme.Primary
import com.smile.mypark.core.ui.theme.White
import com.smile.mypark.presentation.contest.ContestJoinedPage
import com.smile.mypark.presentation.contest.ContestListPage
import kotlinx.coroutines.launch
import mypark.composeapp.generated.resources.Res
import mypark.composeapp.generated.resources.ic_menu_green
import mypark.composeapp.generated.resources.ic_mypark_logo
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun MyParkTopBar(
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier,
    backgroundColor: Color = White,
    menuTint: Color = Primary,
    logoTint: Color? = null,
    logo: @Composable () -> Unit = {
        Image(
            painter = painterResource(Res.drawable.ic_mypark_logo),
            contentDescription = "MY PARK",
            colorFilter = logoTint?.let { ColorFilter.tint(it) },
            modifier = Modifier
                .size(width = 145.dp, height = 48.dp)
        )
    }
) {
    Column(
        modifier
            .fillMaxWidth()
            .background(backgroundColor)

    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(start = 19.dp, top = 61.dp, bottom = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(Modifier.weight(1f)) { logo() }
            IconButton(onClick = onMenuClick) {
                Icon(
                    painter = painterResource(Res.drawable.ic_menu_green),
                    contentDescription = "메뉴",
                    tint = menuTint,
                    modifier = Modifier
                        .size(width = 24.dp, height = 19.dp)
                )
            }
        }
    }
}

@Composable
fun MyParkTabBar(
    tabs: List<String>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    TabRow(
        selectedTabIndex = selectedIndex,
        modifier = modifier.fillMaxWidth(),
        containerColor = Color.White,
        contentColor = Primary,
        indicator = { positions ->
            TabRowDefaults.SecondaryIndicator(
                modifier = Modifier
                    .tabIndicatorOffset(positions[selectedIndex])
                    .height(2.dp),
                color = Primary
            )
        },
    ) {
        tabs.forEachIndexed { i, title ->
            Tab(
                selected = i == selectedIndex,
                onClick = { onSelect(i) },
                text = {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 20.toFixedSp(),
                            color = if (i == selectedIndex) Black else Gray153
                        )
                    )
                }
            )
        }
    }
}

@Composable
fun MyParkTabsWithPager(
    tabs: List<String>,
    startIndex: Int = 0,
    modifier: Modifier = Modifier,
    page: @Composable (index: Int) -> Unit
) {
    val scope = rememberCoroutineScope()
    val pagerState = rememberPagerState(
        initialPage = startIndex,
        pageCount = { tabs.size }
    )

    Column(modifier) {
        MyParkTabBar(
            tabs = tabs,
            selectedIndex = pagerState.currentPage,
            onSelect = { i -> scope.launch { pagerState.animateScrollToPage(i) } }
        )

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.fillMaxSize()
        ) { idx ->
            page(idx)
        }
    }
}

@Composable
fun TournamentHomeScreen(
    onMenuClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val tabs = listOf("대회 리스트", "참여중인 대회")

    Column(modifier.background(Color.White)) {
        MyParkTopBar(onMenuClick = onMenuClick)

        MyParkTabsWithPager(tabs = tabs, startIndex = 0, modifier = Modifier.weight(1f)) { idx ->
            when (idx) {
                0 -> ContestListPage()
                1 -> ContestJoinedPage()
            }
        }
    }
}

@Composable
private fun ContestListPagePreviewStub() {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFF8F8F8)),
        contentAlignment = Alignment.Center
    ) { Text("대회 리스트 (프리뷰)") }
}

@Composable
private fun ContestJoinedPagePreviewStub() {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F7FF)),
        contentAlignment = Alignment.Center
    ) { Text("참여중인 대회 (프리뷰)") }
}

@Preview
@Composable
fun MyParkTopBarPreview() {
    MyParkTopBar(onMenuClick = {})
}

@Preview
@Composable
fun MyParkTabBarPreview() {
    MyParkTabBar(
        tabs = listOf("대회 리스트", "참여중인 대회"),
        selectedIndex = 0,
        onSelect = {}
    )
}

@Preview
@Composable
fun TournamentHomeScreenPreview() {
    MaterialTheme {
        TournamentHomeScreen(onMenuClick = {}, modifier = Modifier.fillMaxSize())
    }
}