package org.alexcawl.iot_connector.viewer.ui.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import org.alexcawl.iot_connector.ui.components.placeholder.EmptyScreen
import org.alexcawl.iot_connector.ui.components.placeholder.LoadingScreen
import org.alexcawl.iot_connector.ui.state.data.TextRepresentationModel
import org.alexcawl.iot_connector.ui.state.data.ThermalDataRepresentationModel
import org.alexcawl.iot_connector.ui.state.data.ThermalMatrixRepresentationModel
import org.alexcawl.iot_connector.ui.state.data.ViewerDataRepresentationModel
import org.alexcawl.iot_connector.ui.theme.ExtendedTheme
import org.alexcawl.iot_connector.ui.util.shimmerEffect
import org.alexcawl.iot_connector.viewer.ui.screen.text.TextViewerScreen
import org.alexcawl.iot_connector.viewer.ui.screen.thermal.ThermalDataViewerScreen
import org.alexcawl.iot_connector.viewer.ui.screen.thermal.ThermalMatrixViewerScreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun ViewerScreen(
    state: ViewerScreenState,
    onAction: (ViewerScreenAction) -> Unit,
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier
) = Scaffold(
    modifier = modifier,
    topBar = {
        CenterAlignedTopAppBar(
            title = {
                when (state) {
                    is ViewerScreenState.Viewer -> Text(
                        text = state.name ?: state.endpoint,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleLarge
                    )
                    else -> Box(
                        modifier = Modifier
                            .height(ExtendedTheme.size.small)
                            .width(ExtendedTheme.size.large)
                            .shimmerEffect()
                    )
                }
            },
            navigationIcon = {
                IconButton(onClick = onNavigateBack) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            },
            actions = {
                IconButton(onClick = { onAction(ViewerScreenAction.Refresh) }) {
                    Icon(imageVector = Icons.Default.Refresh, contentDescription = null)
                }
            }
        )
    }
) { paddingValuesOuter: PaddingValues ->
    val paddingModifier = Modifier
        .padding(paddingValuesOuter)
        .fillMaxSize()
    when (state) {
        is ViewerScreenState.Initial -> LoadingScreen(modifier = paddingModifier)

        is ViewerScreenState.Failure -> EmptyScreen(
            title = {
                Text(
                    text = "It's unable to fetch data from server",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            subtitle = {
                Text(
                    text = "Make sure that you specified host and endpoint correctly and refresh",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.titleLarge
                )
            },
            icon = {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    modifier = Modifier.size(ExtendedTheme.iconSize.big)
                )
            },
            modifier = paddingModifier
        )

        is ViewerScreenState.Viewer -> {
            var selectedIndex by remember { mutableIntStateOf(0) }
            val pagerState = rememberPagerState(
                initialPage = selectedIndex,
                pageCount = state.representations::size
            )

            LaunchedEffect(selectedIndex) {
                pagerState.animateScrollToPage(selectedIndex)
            }

            LaunchedEffect(pagerState) {
                if (!pagerState.isScrollInProgress) {
                    selectedIndex = pagerState.currentPage
                }
            }

            Scaffold(
                modifier = paddingModifier,
                topBar = {
                    TabRow(
                        selectedTabIndex = selectedIndex,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        state.representations.forEachIndexed { i: Int, representation: ViewerDataRepresentationModel ->
                            val (text, icon) = when (representation) {
                                is TextRepresentationModel -> Pair("Text", Icons.Default.Create)
                                is ThermalDataRepresentationModel -> Pair("Thermal Data", Icons.Default.Search)
                                is ThermalMatrixRepresentationModel  -> Pair("Thermal Matrix", Icons.Default.Search)
                                else -> throw IllegalStateException("Such representation is not supported: ${representation.javaClass}")
                            }
                            Tab(
                                selected = selectedIndex == i,
                                onClick = { selectedIndex = i },
                                text = {
                                    Text(text = text)
                                },
                                icon = {
                                    Icon(imageVector = icon, contentDescription = null)
                                }
                            )
                        }
                    }
                }
            ) { paddingValuesInner: PaddingValues ->
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .padding(paddingValuesInner)
                        .fillMaxSize()
                ) {
                    when (val representation = state.representations[it]) {
                        is TextRepresentationModel -> TextViewerScreen(state = representation)
                        is ThermalMatrixRepresentationModel -> ThermalMatrixViewerScreen(state = representation)
                        is ThermalDataRepresentationModel -> ThermalDataViewerScreen(state = representation)
                        else -> throw IllegalStateException("Such representation is not supported: ${representation.javaClass}")
                    }
                }
            }
        }
    }
}