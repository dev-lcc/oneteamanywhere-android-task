package io.github.devlcc.core.data

import io.github.devlcc.core.data.converters.mapToEntity
import io.github.devlcc.core.data.converters.mapToModel
import io.github.devlcc.core.data.di.getCoreDataKoinModule
import io.github.devlcc.core.database.ActivityDao
import io.github.devlcc.core.database.di.testCoreDatabaseKoinModule
import io.github.devlcc.core.database.entities.LevelWithActivitiesEntity
import io.github.devlcc.core.database.fake.FakeLevelsWithActivitiesData
import io.github.devlcc.core.model.ChallengeDayOfTheWeek
import io.github.devlcc.core.model.ChallengeLevel
import io.github.devlcc.core.network.di.testCoreNetworkKoinModule
import io.github.devlcc.core.network.dto.GetActivitiesResponse
import io.github.devlcc.core.network.fake.HomeContentApiServiceMockData
import io.ktor.client.engine.HttpClientEngine
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import org.mobilenativefoundation.store.store5.impl.extensions.fresh
import org.mobilenativefoundation.store.store5.impl.extensions.get
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class ActivityRepositoryTest: KoinTest {

    private val activityRepository: ActivityRepository
        get() = this@ActivityRepositoryTest.get()

    private val testCoroutineScope = TestScope()
    private val testCoroutineDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        startKoin {
            modules(
                getCoreDataKoinModule()
                    + testCoreNetworkKoinModule()
                    + testCoreDatabaseKoinModule(),
            )
        }

        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @AfterTest
    fun tearDown() {
        val activityDao: ActivityDao = get()
        activityDao.removeAll()

        stopKoin()
        Dispatchers.resetMain()
    }

    @Test
    fun `Store - Fresh - Cache ONLY`() = testCoroutineScope.runTest {
        //
        // GIVEN
        //

        // Pre-populate Local Database
        val sampleCount = 3
        val sampleData = FakeLevelsWithActivitiesData.get(sampleCount)
        println("Store - Fresh - Cache ONLY() -> sampleData = \n$sampleData")
        val activityDao: ActivityDao = get()
        activityDao.upsert(*(sampleData).toTypedArray())

        // Assume NO data available from Network source

        val inputKey = ChallengeDayOfTheWeek.entries.random()/*find { day -> day.value == sampleData.random().level.dayOfTheWeek.toInt() }!!*/

        //
        // WHEN
        //
        val actual = activityRepository.store().fresh(key = inputKey)

        //
        // THEN
        //
        println("Store - Fresh - Cache ONLY() -> actual = \n$actual")
        assertTrue { actual.isNotEmpty() }
        assertTrue { actual.all { level -> level.dayOfTheWeek == inputKey } }
    }

    @Test
    fun `Store - Fresh - Network ONLY`() = testCoroutineScope.runTest {
        //
        // GIVEN
        //
        val json: Json = get()

        // Assume NO data available from Local(Database) source yet
        // Prepare Mock Network Response
        val getActivitiesResponse = json.decodeFromString(
            GetActivitiesResponse.serializer(),
            HomeContentApiServiceMockData.GetActivities.success()
        )
        println("Store - Fresh - Network ONLY() -> getActivitiesResponse = \n$getActivitiesResponse")
        val engineModule = module {
            single<HttpClientEngine> { HomeContentApiServiceMockData.GetActivities.mockEngineStatusSuccess() }
        }
        loadKoinModules(engineModule)

        val inputKey = ChallengeDayOfTheWeek.entries.random()
        val expected = getActivitiesResponse.levels
            .map { level ->
                level.mapToEntity(dayOfTheWeek = inputKey).mapToModel(dayOfTheWeek = inputKey)
                    .copy(
                        activities = level.activities.map { activity ->
                            activity.mapToEntity(
                                level = level.level!!.toInt(10),
                                dayOfTheWeek = inputKey,
                                json = json,
                            ).mapToModel(json)
                        }
                    )
            }
        println("Store - Fresh - Network ONLY() -> expected = \n${json.encodeToString(ListSerializer(ChallengeLevel.serializer()), expected)}")


        //
        // WHEN
        //
        val actual = activityRepository.store().fresh(key = inputKey)

        //
        // THEN
        //
        println("Store - Fresh() -> actual = \n${json.encodeToString(ListSerializer(ChallengeLevel.serializer()), actual)}")
        assertTrue { expected.containsAll(actual) }
    }

    @Test
    fun `Store - Fresh - Network With Cache`() = testCoroutineScope.runTest {
        //
        // GIVEN
        //
        val json: Json = get()

        // Prepare Mock Network Response
        val getActivitiesResponse = json.decodeFromString(
            GetActivitiesResponse.serializer(),
            HomeContentApiServiceMockData.GetActivities.success()
        )
        println("Store - Fresh - Network With Cache() -> getActivitiesResponse = \n$getActivitiesResponse")
        val engineModule = module {
            single<HttpClientEngine> { HomeContentApiServiceMockData.GetActivities.mockEngineStatusSuccess() }
        }
        loadKoinModules(engineModule)

        val inputKey = ChallengeDayOfTheWeek.entries.random()
        val expectedFreshValue = getActivitiesResponse.levels
            .map { level ->
                level.mapToEntity(dayOfTheWeek = inputKey).mapToModel(dayOfTheWeek = inputKey)
                    .copy(
                        activities = level.activities.map { activity ->
                            activity.mapToEntity(
                                level = level.level!!.toInt(10),
                                dayOfTheWeek = inputKey,
                                json = json,
                            ).mapToModel(json)
                        }
                    )
            }
        println("Store - Fresh - Network With Cache() -> expected = \n${json.encodeToString(ListSerializer(ChallengeLevel.serializer()), expectedFreshValue)}")

        // Pre-populate Local Database
        val expectedCachedValue = expectedFreshValue.subList(0, expectedFreshValue.size - 2)
        val cacheSampleData = expectedCachedValue.map { level ->
            LevelWithActivitiesEntity(
                level = level.mapToEntity(dayOfTheWeek = inputKey),
                activities = level.activities.map { activity -> activity.mapToEntity(level = level.level!!.toInt(), dayOfTheWeek = inputKey, json = json) }
            )

        }
        println("Store - Fresh - Network With Cache() -> cacheSampleData = \n$cacheSampleData")
        val activityDao: ActivityDao = get()
        activityDao.upsert(*(cacheSampleData).toTypedArray())


        //
        // WHEN
        //
        val actualCachedValue = activityRepository.store().get(key = inputKey)
        val actualFreshValue = activityRepository.store().fresh(key = inputKey)

        //
        // THEN
        //
        println("Store - Fresh - Network With Cache() -> actualCachedValue = \n${json.encodeToString(ListSerializer(ChallengeLevel.serializer()), actualCachedValue)}")
        assertTrue { expectedCachedValue.containsAll(actualCachedValue) }

        println("Store - Fresh - Network With Cache() -> actualFreshValue = \n${json.encodeToString(ListSerializer(ChallengeLevel.serializer()), actualFreshValue)}")
        assertTrue { expectedFreshValue.containsAll(actualFreshValue) }
    }

}