package io.github.devlcc.core.database.fake

import io.github.devlcc.core.database.Activity
import io.github.devlcc.core.database.ActivityLevel
import io.github.devlcc.core.database.entities.LevelWithActivitiesEntity
import io.github.devlcc.core.model.ChallengeActivity
import io.github.devlcc.core.model.ChallengeDayOfTheWeek
import org.jetbrains.annotations.VisibleForTesting
import java.util.UUID
import kotlin.random.Random

@VisibleForTesting
object FakeLevelsWithActivitiesData {

    fun get(count: Int, activitiesPerLevelCount: Int = 3): List<LevelWithActivitiesEntity> =
        mutableListOf<LevelWithActivitiesEntity>().apply {
            repeat(count) {
                val levelValue = Random.nextLong(1L, 100L)
                val dayOfWeek = ChallengeDayOfTheWeek.entries.random().value.toLong()
                add(
                    LevelWithActivitiesEntity(
                        level = ActivityLevel(
                            level = levelValue,
                            title = "Level $levelValue",
                            description = "Sample description for Level $levelValue",
                            state = ChallengeActivity.State.entries.random().value,
                            dayOfTheWeek = dayOfWeek,
                        ),
                        activities = mutableListOf<Activity>().apply {
                            repeat(activitiesPerLevelCount) { index ->
                                val activityId = UUID.randomUUID().toString()
                                val challengeId = activityId.substring(0..activityId.length / 2)
                                add(
                                    Activity(
                                        id = activityId,
                                        challengeId = challengeId,
                                        type = ChallengeActivity.Type.entries.random().value,
                                        title = "Activity #$index",
                                        titleB = null,
                                        description = "Description of Activity #$index",
                                        descriptionB = null,
                                        state = ChallengeActivity.State.entries.random().value,
                                        icon = null,
                                        lockedIcon = null,
                                        level = levelValue,
                                        dayOfTheWeek = dayOfWeek,
                                    )
                                )
                            }
                        },
                    )
                )
            }
        }
}