package io.github.devlcc.core.data.di

import app.cash.sqldelight.db.SqlDriver
import io.github.devlcc.core.data.ActivityRepository
import io.github.devlcc.core.data.UserRepository
import io.github.devlcc.core.database.AppDatabase
import io.github.devlcc.core.database.di.getCoreDatabaseKoinModule
import io.github.devlcc.core.database.getAndroidSqliteDriver
import io.github.devlcc.core.network.di.getCoreNetworkKoinModule
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun getCoreDataKoinModule(
    restApiEndpoint: String = "https://private-905cf-otaandroidtask.apiary-mock.com",
    isDebug: Boolean = true,
): Module = module {

    single<Json> {
        Json {
            prettyPrint = true
            isLenient = true
            ignoreUnknownKeys = true
        }
    }

    single<CoroutineDispatcher>(named("IOCoroutineDispatcher")) {
        Dispatchers.IO
    }

    single<SqlDriver> {
        AppDatabase.getAndroidSqliteDriver(
            context = get(),
        )
    }

    includes(
        getCoreNetworkKoinModule(
            restApiEndpoint = restApiEndpoint,
            isDebug = isDebug,
        ),
        getCoreDatabaseKoinModule()
    )

    single {
        ActivityRepository(
            activityApiService = get(),
            activityLocalDao = get(),
            json = get(),
        )
    }

    single {
        UserRepository()
    }
    // TODO:: Define other Repository instances here...
}