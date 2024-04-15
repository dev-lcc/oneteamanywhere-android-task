package io.github.devlcc.ota.ui.presentation.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshContainer
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.flowWithLifecycle
import io.github.devlcc.core.model.ChallengeActivity
import io.github.devlcc.core.model.ChallengeDayOfTheWeek
import io.github.devlcc.core.model.ChallengeLevel
import io.github.devlcc.core.model.User
import io.github.devlcc.ota.R
import io.github.devlcc.ota.ui.designsystem.theme.AndroidTaskTheme
import io.github.devlcc.ota.ui.designsystem.theme.PurpleAccent
import io.github.devlcc.ota.ui.designsystem.theme.TextTertiary
import io.github.devlcc.ota.ui.presentation.home.components.ChallengeLevelItemView
import io.github.devlcc.ota.ui.presentation.home.components.FakeIconData
import io.github.devlcc.ota.ui.presentation.home.components.HomeScreenTopBar
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.androidx.compose.koinViewModel
import java.util.UUID
import kotlin.random.Random

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun HomeScreen(
    modifier: Modifier,
    viewModel: HomeScreenViewModel = koinViewModel(),
    onNavigateToMyAccount: (() -> Unit) = {},
    onNavigateToActivity: ((ChallengeActivity) -> Unit) = {},
    onNavigateToJourney: (() -> Unit) = {},
) {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val lifecycleAwareFlow = remember(viewModel.uiState, lifecycleOwner) {
        viewModel.uiState.flowWithLifecycle(lifecycleOwner.lifecycle)
    }
    val uiState by lifecycleAwareFlow.collectAsState(initial = viewModel.uiState.value)

    HomeScreen(
        modifier = modifier,
        uiState = uiState,
        onEvent = viewModel::handle,
    )

    LaunchedEffect(Unit) {
        viewModel.effect
            .onEach { effect ->
                when(effect) {
                    is HomeScreenEffect.ErrorFetchActivities -> {
                        Toast.makeText(
                            context,
                            R.string.something_went_wrong_try_again,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is HomeScreenEffect.NavigateToMyAccount -> {
                        onNavigateToMyAccount()
                    }
                    is HomeScreenEffect.NavigateToActivity -> {
                        onNavigateToActivity(effect.which)
                    }
                    is HomeScreenEffect.NavigateToJourney -> {
                        onNavigateToJourney()
                    }
                }
            }
            .launchIn(this)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier,
    uiState: HomeScreenUiState,
    onEvent: ((HomeScreenEvent) -> Unit) = {},
) {

    val lazyListState = rememberLazyListState()
    val pullRefreshState = rememberPullRefreshState(
        refreshing = uiState.isLoading,
        onRefresh = {
            onEvent(HomeScreenEvent.Refresh)
        }
    )

    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            HomeScreenTopBar(
                modifier = Modifier.fillMaxWidth(),
                uiState = uiState,
                onTapMyAccount = { onEvent(HomeScreenEvent.NavigateToMyAccount) },
                onSelectDay = { selectedDay -> onEvent(HomeScreenEvent.ToggleSelectedDay(selectedDay)) },
            )
        },
    ) { paddingValues ->

        Box(
            Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                when(val content = uiState.content) {
                    //
                    // Empty Content
                    //
                    is HomeScreenUiState.HomeContent.Empty -> {
                        Column(
                            modifier = Modifier
                                /*.fillMaxSize()*/
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(
                                    top = paddingValues.calculateTopPadding(),
                                    start = 32.dp,
                                    end = 32.dp,
                                    bottom = 32.dp
                                )
                                .pullRefresh(pullRefreshState)
                                .verticalScroll(rememberScrollState()),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            Text(
                                text = stringResource(id = R.string.home_empty_content_label),
                                style = MaterialTheme.typography.displayLarge,
                                textAlign = TextAlign.Center,
                            )
                        }
                    }   // END:: Empty Content

                    //
                    // Challenge Levels Content
                    //
                    is HomeScreenUiState.HomeContent.Items -> {
                        LazyColumn(
                            modifier = Modifier
                                /*.fillMaxSize()*/
                                .fillMaxWidth()
                                .weight(1f)
                                .padding(top = paddingValues.calculateTopPadding())
                                .pullRefresh(pullRefreshState)
                            ,
                            state = lazyListState,
                        ) {
                            items(
                                count = content.data.size,
                                key = { index -> index/*content.data[index].level ?: 0*/ },
                            ) { index ->
                                val levelItem = content.data[index]
                                ChallengeLevelItemView(
                                    modifier = Modifier,
                                    item = levelItem,
                                    onTapChallengeActivity = { which ->
                                        onEvent(HomeScreenEvent.NavigateToActivity(which))
                                    },
                                )
                            }
                        }
                    }   // END:: Challenge Levels Content
                }

                Spacer(
                    Modifier
                        .height(1.dp)
                        .background(TextTertiary))
                IconButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    onClick = {
                        onEvent(HomeScreenEvent.NavigateToJourney)
                    },
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_btn_journey),
                            contentDescription = "Journey",
                        )
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = "Journey",
                            style = MaterialTheme.typography.displaySmall.copy(fontWeight = FontWeight.Medium),
                            color = PurpleAccent,
                        )
                        Spacer(Modifier.height(4.dp))
                    }
                }
            }

            // Pull refresh indicator
            PullRefreshIndicator(
                refreshing = uiState.isLoading,
                state = pullRefreshState,
                modifier = Modifier.align(Alignment.TopCenter)
            )
        }
    }
}


@Preview
@Composable
fun HomeScreen_Preview_Empty() {
    AndroidTaskTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                uiState = HomeScreenUiState(
                    isLoading = false,
                    journeyStatus = User.JourneyStatus(progress = Random.nextInt(100)),
                    firePoints = Random.nextInt(300),
                    selectedDay = ChallengeDayOfTheWeek.entries.random(),
                    content = HomeScreenUiState.HomeContent.Empty,
                )
            )
        }
    }
}

@Preview
@Composable
fun HomeScreen_Preview_WithContent() {
    AndroidTaskTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
        ) {
            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                uiState = HomeScreenUiState(
                    isLoading = false,
                    journeyStatus = User.JourneyStatus(progress = Random.nextInt(100)),
                    firePoints = Random.nextInt(300),
                    selectedDay = ChallengeDayOfTheWeek.entries.random(),
                    content = HomeScreenUiState.HomeContent.Items(
                        data = mutableListOf<ChallengeLevel>().apply {
                            val levelsCount = Random.nextInt(2,5)
                            repeat(levelsCount) { levelIndex ->
                                add(
                                    ChallengeLevel(
                                        level = levelIndex,
                                        title = "Random Level Title #$levelIndex",
                                        description = "This is some random level description #$levelIndex",
                                        state = ChallengeLevel.State.Locked,
                                        activities = mutableListOf<ChallengeActivity>().apply {
                                            val activitiesCount = Random.nextInt(4)
                                            repeat(activitiesCount) { index ->
                                                val activityId = UUID.randomUUID().toString()
                                                val iconIndex = Random.nextInt(FakeIconData.Icons.size)
                                                add(
                                                    ChallengeActivity(
                                                        id = activityId,
                                                        challengeId = activityId.substring(0..(activityId.length/2)),
                                                        type = ChallengeActivity.Type.entries.random(),
                                                        title = "Activity Title #$index",
                                                        description = "This is some description for activity #$index",
                                                        state = ChallengeActivity.State.entries.random(),
                                                        icon = FakeIconData.Icons[iconIndex],
                                                        lockedIcon = FakeIconData.LockedIcons[iconIndex],
                                                    )
                                                )
                                            }
                                        },
                                    )
                                )
                            }
                        },
                    ),
                )
            )
        }
    }
}