package io.github.devlcc.ota.ui.presentation.home

import app.cash.turbine.test
import io.github.devlcc.core.data.di.getCoreDataKoinModule
import io.github.devlcc.core.database.ActivityDao
import io.github.devlcc.core.database.di.testCoreDatabaseKoinModule
import io.github.devlcc.core.network.di.testCoreNetworkKoinModule
import io.github.devlcc.ota.di.getViewModelsKoinModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.get
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class HomeScreenViewModelTest: KoinTest {

    private val viewModel: HomeScreenViewModel
        get() = this@HomeScreenViewModelTest.get()

    private val testCoroutineScope = TestScope()
    private val testCoroutineDispatcher = StandardTestDispatcher()

    @BeforeTest
    fun setup() {
        startKoin {
            modules(
                getViewModelsKoinModule()
                        + getCoreDataKoinModule()
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

    // TODO:: Setup proper conditions to assert test using cash.app.turbine to simulate HomeScreenUiState
    @Test
    fun `Fresh Fetch() - Success`() = testCoroutineScope.runTest {
        //
        // TODO:: GIVEN
        //

        //
        // WHEN
        //
        viewModel.handle(HomeScreenEvent.Refresh)

        //
        // THEN
        //
        viewModel.uiState.test {
            var state: HomeScreenUiState
            do {
                state = awaitItem()
            } while (state.isLoading)

            assertTrue { state.content is HomeScreenUiState.HomeContent.Items }
        }
    }

}