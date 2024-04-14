package io.github.devlcc.core.database

import io.github.devlcc.core.database.di.testCoreDatabaseKoinModule
import io.github.devlcc.core.database.fake.FakeLevelsWithActivitiesData
import kotlinx.coroutines.test.runTest
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
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
        val expected = FakeLevelsWithActivitiesData.get(sampleCount)
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
        val levelWithActivities = FakeLevelsWithActivitiesData.get(sampleCount).random()
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
        val levelWithActivities = FakeLevelsWithActivitiesData.get(sampleCount).random()
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
        val levelWithActivities = FakeLevelsWithActivitiesData.get(sampleCount).random()
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
        val levelWithActivities = FakeLevelsWithActivitiesData.get(sampleCount).random()
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
