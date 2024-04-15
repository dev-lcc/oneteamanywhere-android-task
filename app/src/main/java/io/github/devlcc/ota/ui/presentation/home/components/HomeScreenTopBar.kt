package io.github.devlcc.ota.ui.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.devlcc.core.model.ChallengeDayOfTheWeek
import io.github.devlcc.core.model.User
import io.github.devlcc.ota.R
import io.github.devlcc.ota.ui.designsystem.theme.AndroidTaskTheme
import io.github.devlcc.ota.ui.designsystem.theme.PurpleAccent
import io.github.devlcc.ota.ui.presentation.home.HomeScreenUiState
import kotlin.random.Random

internal const val HomeScreenTopBarHeight: Int = 148

@Composable
fun HomeScreenTopBar(
    modifier: Modifier,
    uiState: HomeScreenUiState,
    onTapMyAccount: (() -> Unit) = {},
    onSelectDay: ((ChallengeDayOfTheWeek) -> Unit) = {},
) {

    //
    // Column ~ TopBar Root Content
    //
    Column(
        modifier = modifier
            .height(HomeScreenTopBarHeight.dp)
            .fillMaxWidth()
            .padding(start = 12.dp, end = 12.dp, top = 16.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top,
    ) {

        //
        // Row ~ Emotional State | Fire Points | Thumbnail Profile Icon
        //
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            //
            // Emotional State Icon
            //
            Image(
                modifier = Modifier.size(40.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_journey_status),
                contentDescription = "Journey Status",
            )

            Spacer(modifier = Modifier.width(12.dp))

            //
            // Emotional State Label and Progress
            //
            Column {
                Text(
                    modifier = Modifier,
                    text = when(val status = uiState.journeyStatus) {
                        null -> "---"
                        else -> {
                            stringResource(
                                id = when(status.state) {
                                    User.JourneyStatus.State.Angry -> R.string.journey_status_angry
                                    User.JourneyStatus.State.TamingTemper -> R.string.journey_status_taming_temper
                                    User.JourneyStatus.State.Calm -> R.string.journey_status_calm
                                    User.JourneyStatus.State.Interested -> R.string.journey_status_interested
                                    User.JourneyStatus.State.Happy -> R.string.journey_status_happy
                                }
                            )
                        }
                    },
                    style = MaterialTheme.typography.bodyLarge,
                )
                Spacer(Modifier.height(4.dp))

                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    LinearProgressIndicator(
                        modifier = Modifier.width(72.dp),
                        progress = { (uiState.journeyStatus?.progress ?: 0).toFloat() / 100f }
                    )
                    Spacer(Modifier.width(8.dp))
                    Text(
                        text = "${uiState.journeyStatus?.progress ?: 0}%",
                        style = MaterialTheme.typography.labelSmall,
                        color = PurpleAccent,
                    )
                }
            }   // END:: Column ~ Emotional State Labels and Progress

            Spacer(Modifier.weight(1f))

            // Fire Points
            Image(
                modifier = Modifier.size(24.dp),
                imageVector = ImageVector.vectorResource(R.drawable.ic_fire),
                contentDescription = "Fire",
            )
            Text(
                text = "${uiState.firePoints}",
                style = MaterialTheme.typography.displayMedium,
                color = PurpleAccent,
            )

            Spacer(Modifier.width(12.dp))

            // Thumbnail Profile Icon
            IconButton(
                modifier = Modifier.size(40.dp),
                onClick = { onTapMyAccount() }
            ) {
                Image(
                    modifier = Modifier.fillMaxSize(),
                    imageVector = ImageVector.vectorResource(R.drawable.ic_myaccount_round_thumbnail),
                    contentDescription = "Fire",
                )
            }

        }   // END:: Row ~ Emotional State | Fire Points | Thumbnail Profile Icon

        Spacer(Modifier.height(16.dp))

        //
        // TabRow ~ Day Tracker
        //
        TabRow(
            selectedTabIndex = uiState.selectedDay.value,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Tab(
                selected = uiState.selectedDay == ChallengeDayOfTheWeek.Monday,
                onClick = { onSelectDay(ChallengeDayOfTheWeek.Monday) }
            ) {
                DayTrackerTabView(
                    day = ChallengeDayOfTheWeek.Monday,
                    isSelected = uiState.selectedDay == ChallengeDayOfTheWeek.Monday,
                )
            }
            Tab(
                selected = uiState.selectedDay == ChallengeDayOfTheWeek.Tuesday,
                onClick = { onSelectDay(ChallengeDayOfTheWeek.Tuesday) }
            ) {
                DayTrackerTabView(
                    day = ChallengeDayOfTheWeek.Tuesday,
                    isSelected = uiState.selectedDay == ChallengeDayOfTheWeek.Tuesday,
                )
            }
            Tab(
                selected = uiState.selectedDay == ChallengeDayOfTheWeek.Wednesday,
                onClick = { onSelectDay(ChallengeDayOfTheWeek.Wednesday) }
            ) {
                DayTrackerTabView(
                    day = ChallengeDayOfTheWeek.Wednesday,
                    isSelected = uiState.selectedDay == ChallengeDayOfTheWeek.Wednesday,
                )
            }
            Tab(
                selected = uiState.selectedDay == ChallengeDayOfTheWeek.Thursday,
                onClick = { onSelectDay(ChallengeDayOfTheWeek.Thursday) }
            ) {
                DayTrackerTabView(
                    day = ChallengeDayOfTheWeek.Thursday,
                    isSelected = uiState.selectedDay == ChallengeDayOfTheWeek.Thursday,
                )
            }
            Tab(
                selected = uiState.selectedDay == ChallengeDayOfTheWeek.Friday,
                onClick = { onSelectDay(ChallengeDayOfTheWeek.Friday) }
            ) {
                DayTrackerTabView(
                    day = ChallengeDayOfTheWeek.Friday,
                    isSelected = uiState.selectedDay == ChallengeDayOfTheWeek.Friday,
                )
            }
            Tab(
                selected = uiState.selectedDay == ChallengeDayOfTheWeek.Saturday,
                onClick = { onSelectDay(ChallengeDayOfTheWeek.Saturday) }
            ) {
                DayTrackerTabView(
                    day = ChallengeDayOfTheWeek.Saturday,
                    isSelected = uiState.selectedDay == ChallengeDayOfTheWeek.Saturday,
                )
            }
            Tab(
                selected = uiState.selectedDay == ChallengeDayOfTheWeek.Sunday,
                onClick = { onSelectDay(ChallengeDayOfTheWeek.Sunday) }
            ) {
                DayTrackerTabView(
                    day = ChallengeDayOfTheWeek.Sunday,
                    isSelected = uiState.selectedDay == ChallengeDayOfTheWeek.Sunday,
                )
            }
        }   // END:: TabRow ~ Day Tracker

    }   // END:: Column ~ TopBar Root Content

}



@Composable
@Preview(heightDp = 148)
fun HomeScreenTopBar_Preview_Monday() {
    AndroidTaskTheme {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            HomeScreenTopBar(
                modifier = Modifier.fillMaxWidth(),
                uiState = run {
                    val journeyProgress = Random.nextInt(0, 100)
                    val journeyStatus = User.JourneyStatus(progress = journeyProgress)
                    val firePoints = Random.nextInt(300)
                    HomeScreenUiState(
                        journeyStatus = journeyStatus,
                        firePoints = firePoints,
                        selectedDay = ChallengeDayOfTheWeek.Monday,
                    )
                }
            )
        }
    }
}

@Composable
@Preview(heightDp = 148)
fun HomeScreenTopBar_Preview_Tuesday() {
    AndroidTaskTheme {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            HomeScreenTopBar(
                modifier = Modifier.fillMaxWidth(),
                uiState = run {
                    val journeyProgress = Random.nextInt(0, 100)
                    val journeyStatus = User.JourneyStatus(progress = journeyProgress)
                    val firePoints = Random.nextInt(300)
                    HomeScreenUiState(
                        journeyStatus = journeyStatus,
                        firePoints = firePoints,
                        selectedDay = ChallengeDayOfTheWeek.Tuesday,
                    )
                }
            )
        }
    }
}

@Composable
@Preview(heightDp = 148)
fun HomeScreenTopBar_Preview_Wednesday() {
    AndroidTaskTheme {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            HomeScreenTopBar(
                modifier = Modifier.fillMaxWidth(),
                uiState = run {
                    val journeyProgress = Random.nextInt(0, 100)
                    val journeyStatus = User.JourneyStatus(progress = journeyProgress)
                    val firePoints = Random.nextInt(300)
                    HomeScreenUiState(
                        journeyStatus = journeyStatus,
                        firePoints = firePoints,
                        selectedDay = ChallengeDayOfTheWeek.Wednesday,
                    )
                }
            )
        }
    }
}

@Composable
@Preview(heightDp = 148)
fun HomeScreenTopBar_Preview_Thursday() {
    AndroidTaskTheme {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            HomeScreenTopBar(
                modifier = Modifier.fillMaxWidth(),
                uiState = run {
                    val journeyProgress = Random.nextInt(0, 100)
                    val journeyStatus = User.JourneyStatus(progress = journeyProgress)
                    val firePoints = Random.nextInt(300)
                    HomeScreenUiState(
                        journeyStatus = journeyStatus,
                        firePoints = firePoints,
                        selectedDay = ChallengeDayOfTheWeek.Thursday,
                    )
                }
            )
        }
    }
}

@Composable
@Preview(heightDp = 148)
fun HomeScreenTopBar_Preview_Friday() {
    AndroidTaskTheme {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            HomeScreenTopBar(
                modifier = Modifier.fillMaxWidth(),
                uiState = run {
                    val journeyProgress = Random.nextInt(0, 100)
                    val journeyStatus = User.JourneyStatus(progress = journeyProgress)
                    val firePoints = Random.nextInt(300)
                    HomeScreenUiState(
                        journeyStatus = journeyStatus,
                        firePoints = firePoints,
                        selectedDay = ChallengeDayOfTheWeek.Friday,
                    )
                }
            )
        }
    }
}

@Composable
@Preview(heightDp = 148)
fun HomeScreenTopBar_Preview_Saturday() {
    AndroidTaskTheme {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            HomeScreenTopBar(
                modifier = Modifier.fillMaxWidth(),
                uiState = run {
                    val journeyProgress = Random.nextInt(0, 100)
                    val journeyStatus = User.JourneyStatus(progress = journeyProgress)
                    val firePoints = Random.nextInt(300)
                    HomeScreenUiState(
                        journeyStatus = journeyStatus,
                        firePoints = firePoints,
                        selectedDay = ChallengeDayOfTheWeek.Saturday,
                    )
                }
            )
        }
    }
}

@Composable
@Preview(heightDp = 148)
fun HomeScreenTopBar_Preview_Sunday() {
    AndroidTaskTheme {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            HomeScreenTopBar(
                modifier = Modifier.fillMaxWidth(),
                uiState = run {
                    val journeyProgress = Random.nextInt(0, 100)
                    val journeyStatus = User.JourneyStatus(progress = journeyProgress)
                    val firePoints = Random.nextInt(300)
                    HomeScreenUiState(
                        journeyStatus = journeyStatus,
                        firePoints = firePoints,
                        selectedDay = ChallengeDayOfTheWeek.Sunday,
                    )
                }
            )
        }
    }
}
