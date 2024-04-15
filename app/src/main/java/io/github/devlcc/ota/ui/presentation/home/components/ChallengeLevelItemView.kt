package io.github.devlcc.ota.ui.presentation.home.components

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.devlcc.core.model.ChallengeActivity
import io.github.devlcc.core.model.ChallengeLevel
import io.github.devlcc.ota.R
import io.github.devlcc.ota.ui.designsystem.theme.AndroidTaskTheme
import io.github.devlcc.ota.ui.designsystem.theme.TextPrimary
import io.github.devlcc.ota.ui.designsystem.theme.TextSecondary
import java.util.UUID
import kotlin.random.Random

@Composable
fun ChallengeLevelItemView(
    modifier: Modifier,
    item: ChallengeLevel,
    onTapChallengeActivity: ((ChallengeActivity) -> Unit) = {},
) {
    //
    // Root Content
    //
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {

        //
        // Level Header Icon
        //
        Box(
            modifier = Modifier.size(120.dp),
            contentAlignment = Alignment.Center,
        ) {
            
            Image(
                modifier = Modifier
                    .width(102.dp)
                    .height(86.dp),
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_challenge_chapter),
                contentDescription = "Level ${item.level ?: 0}",
            )

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(TextPrimary)
                    .align(Alignment.BottomCenter),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
                    text = "Level ${item.level ?: 0}".uppercase(),
                    color = Color.White,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
        }   // END:: Box ~ Level Header Icon
        Spacer(Modifier.height(12.dp))

        // Level Title
        Text(
            modifier = Modifier.padding(horizontal = 16.dp),
            text = item.title ?: "---",
            style = MaterialTheme.typography.displayLarge,
        )
        Spacer(Modifier.height(8.dp))

        // Level Description
        Text(
            modifier = Modifier.padding(horizontal = 32.dp),
            text = item.description ?: "---",
            style = MaterialTheme.typography.displaySmall,
            color = TextSecondary,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(16.dp))

        when(item.activities.isEmpty()) {
            //
            // NO Challenge Activities available...
            //
            true -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = stringResource(id = R.string.home_level_empty_activities),
                        style = MaterialTheme.typography.displayMedium,
                        textAlign = TextAlign.Center,
                    )
                }
            }

            //
            // List of Challenge Activities 
            //
            false -> {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(
                            ((ChallengeActivityItemViewSize * (item.activities.size / 2)) + (24 * (item.activities.size / 2))).dp
                        ),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.Center,
                    userScrollEnabled = false,
                ) {
                    items(
                        count = item.activities.size,
                        key = { index -> index/*item.activities[index].id ?: ""*/ },
                        span = { index ->
                            if(index == item.activities.size - 1 && index%2 == 0) {
                                GridItemSpan(2)
                            } else {
                                GridItemSpan(1)
                            }

                        },
                    ) { index ->
                        val activityItem = item.activities[index]
                        ChallengeActivityItemView(
                            modifier = Modifier,
                            item = activityItem,
                            onTapChallengeActivity = onTapChallengeActivity,
                        )
                    }
                }
            }   // END:: List of Challenge Activities
        }

    }   // END:: Column ~ Root Content

}

@Preview
@Composable
fun ChallengeLevelItemView_Preview_Empty_Activity() {
    AndroidTaskTheme {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            ChallengeLevelItemView(
                modifier = Modifier,
                item = ChallengeLevel(
                    level = Random.nextInt(25),
                    title = "Find your tools",
                    description = "Collect the best ways for you to notice and manage anger",
                    state = ChallengeLevel.State.Available,
                    activities = listOf(),
                ),
            )
        }
    }

}

@Preview
@Composable
fun ChallengeLevelItemView_Preview_Available() {
    AndroidTaskTheme {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            ChallengeLevelItemView(
                modifier = Modifier,
                item = ChallengeLevel(
                    level = Random.nextInt(25),
                    title = "Find your tools",
                    description = "Collect the best ways for you to notice and manage anger",
                    state = ChallengeLevel.State.Available,
                    activities = mutableListOf<ChallengeActivity>().apply {
                        val activitiesCount = 3/*Random.nextInt(2, 4)*/
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
                ),
            )
        }
    }

}

@Preview
@Composable
fun ChallengeLevelItemView_Preview_Locked() {
    AndroidTaskTheme {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            ChallengeLevelItemView(
                modifier = Modifier,
                item = ChallengeLevel(
                    level = Random.nextInt(25),
                    title = "Understand your anger",
                    description = "Uncover what's behind your outbursts and how to prevent them",
                    state = ChallengeLevel.State.Locked,
                    activities = mutableListOf<ChallengeActivity>().apply {
                        val activitiesCount = 4/*Random.nextInt(2,4)*/
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
                ),
            )
        }
    }

}
