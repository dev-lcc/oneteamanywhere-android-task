package io.github.devlcc.core.network

import io.github.devlcc.core.model.ChallengeDayOfTheWeek
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
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.fail

class HomeContentApiServiceTest : KoinTest {

    private val homeContentApiService: HomeContentApiService
        get() = this@HomeContentApiServiceTest.get()

    private val testCoroutineScope = TestScope()
    private val testCoroutineDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        startKoin {
            modules(
                testCoreNetworkKoinModule()
            )
        }

        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
        Dispatchers.resetMain()
    }

    @Test
    fun `Get Activities - Success`() = testCoroutineScope.runTest {
        //
        // GIVEN
        //
        val json: Json = get()
        val engineModule = module {
            single<HttpClientEngine> { HomeContentApiServiceMockData.GetActivities.mockEngineStatusSuccess() }
        }
        loadKoinModules(engineModule)

        val inputActivityDayOfWeek = ChallengeDayOfTheWeek.entries.random()
        val expected = json.decodeFromString(
            GetActivitiesResponse.serializer(),
            HomeContentApiServiceMockData.GetActivities.success()
        )

        //
        // WHEN
        //
        try {
            val actual = withContext(Dispatchers.IO) {
                homeContentApiService.getActivities(whichDay = inputActivityDayOfWeek)
            }

            //
            // THEN
            //
            println(
                "Get Activities - Success() -> actual = \n${
                    json.encodeToString(
                        GetActivitiesResponse.serializer(),
                        actual
                    )
                }"
            )
            assertTrue { actual == expected }
        } catch (err: Throwable) {
            err.printStackTrace()
            fail(err.message)
        } finally {
            unloadKoinModules(engineModule)
        }

    }

    @Test
    fun `Get Activities - Error`() = testCoroutineScope.runTest {
        //
        // GIVEN
        //
        val json: Json = get()
        val engineModule = module {
            single<HttpClientEngine> { HomeContentApiServiceMockData.GetActivities.mockEngineStatusError() }
        }
        loadKoinModules(engineModule)

        val inputActivityDayOfWeek = ChallengeDayOfTheWeek.entries.random()

        //
        // WHEN
        //
        try {
            withContext(Dispatchers.IO) {
                homeContentApiService.getActivities(whichDay = inputActivityDayOfWeek)
            }

            //
            // THEN
            //
            fail("Get Activities - Error() -> Must fail...")
        } catch (err: Throwable) {
            err.printStackTrace()
            println("Get Activities - Error() -> err = ${err.message}")

            // Assert Error expected
            assertTrue { true }
        } finally {
            unloadKoinModules(engineModule)
        }

    }

}