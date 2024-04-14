package io.github.devlcc.core.database

import io.github.devlcc.core.database.di.testCoreDatabaseKoinModule
import io.github.devlcc.core.database.entities.LevelWithActivitiesEntity
import io.github.devlcc.core.model.ChallengeActivity
import io.github.devlcc.core.model.ChallengeDayOfTheWeek
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import java.util.UUID
import kotlin.random.Random
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class ActivityDaoTest : KoinTest {

    private val activityDao: ActivityDao
        get() = this@ActivityDaoTest.get()

    @BeforeTest
    fun setup() {
        startKoin {
            modules(testCoreDatabaseKoinModule())
        }
    }

    @AfterTest
    fun tearDown() {
        activityDao.removeAll()
        stopKoin()
    }

    @Test
    fun `Get All Activities`() = runTest {
        //
        // GIVEN
        //
        val sampleCount = 3
        val expected = LevelsWithActivitiesMockData.get(sampleCount)
        println("Get All Activities() -> expected = \n$expected")

        activityDao.upsert(*(expected).toTypedArray())

        //
        // WHEN
        //
        val actual = activityDao.getAllActivities()
        println("Get All Activities() -> actual = \n$actual")

        //
        // THEN
        //
        assertTrue { actual.containsAll(expected) }

    }

    @Test
    fun `Get Activity By Id`() = runTest {
        //
        // GIVEN
        //
        val sampleCount = 3
        val levelWithActivities = LevelsWithActivitiesMockData.get(sampleCount).random()
        activityDao.upsert(levelWithActivities)
        val expected = levelWithActivities.activities.random()
        println("Get Activity By Id() -> expected = \n$expected")

        val inputId = expected.id

        //
        // WHEN
        //
        val actual = activityDao.getActivityById(inputId)
        println("Get Activity By Id() -> actual = \n$actual")

        //
        // THEN
        //
        assertTrue { actual == expected }

    }

    @Test
    fun `Remove Activity By Id`() = runTest {
        //
        // GIVEN
        //
        val sampleCount = 3
        val levelWithActivities = LevelsWithActivitiesMockData.get(sampleCount).random()
        activityDao.upsert(levelWithActivities)
        val expected = levelWithActivities.activities.random()
        println("Remove Activity By Id() -> expected = \n$expected")

        val inputId = expected.id

        //
        // WHEN
        //
        activityDao.removeActivityById(inputId)


        //
        // THEN
        //
        val actual = activityDao.getAllActivities()
        println("Remove Activity By Id() -> actual = \n$actual")
        assertTrue {
            actual.none { lvlActivities ->
                lvlActivities.activities.any { activity ->
                    activity.id == inputId
                }
            }
        }

    }

    @Test
    fun `Remove Activities By Day and Level`() = runTest {
        //
        // GIVEN
        //
        val sampleCount = 3
        val levelWithActivities = LevelsWithActivitiesMockData.get(sampleCount).random()
        activityDao.upsert(levelWithActivities)
        val expected = levelWithActivities.activities.random()
        println("Remove Activities By Day and Level() -> expected = \n$expected")

        val inputDay = expected.dayOfTheWeek.toInt()
        val inputLevel = expected.level.toInt()

        //
        // WHEN
        //
        activityDao.removeActivities(
            day = inputDay,
            level = inputLevel,
        )


        //
        // THEN
        //
        val actual = activityDao.getAllActivities()
        println("Remove Activities By Day and Level() -> actual = \n$actual")
        assertTrue {
            actual.none { lvlActivities ->
                lvlActivities.activities.any { activity ->
                    activity.dayOfTheWeek == inputDay.toLong()
                            || activity.level == inputLevel.toLong()
                }
            }
        }

    }

    @Test
    fun `Remove All Activities`() = runTest {
        //
        // GIVEN
        //
        val sampleCount = 3
        val levelWithActivities = LevelsWithActivitiesMockData.get(sampleCount).random()
        activityDao.upsert(levelWithActivities)

        //
        // WHEN
        //
        activityDao.removeAll()

        //
        // THEN
        //
        val actual = activityDao.getAllActivities()
        println("Remove All Activities() -> actual = \n$actual")
        assertTrue {
            actual.isEmpty()
        }

    }

}

object LevelsWithActivitiesMockData {

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