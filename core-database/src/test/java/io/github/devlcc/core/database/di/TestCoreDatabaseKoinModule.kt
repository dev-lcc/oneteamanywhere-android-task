package io.github.devlcc.core.database.di

import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import io.github.devlcc.core.database.OTAAndroidTaskDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.jetbrains.annotations.VisibleForTesting
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

@VisibleForTesting
fun testCoreDatabaseKoinModule(): List<Module> {
    val sqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        .also {
            OTAAndroidTaskDatabase.Schema.create(it)
        }
    return getCoreDatabaseKoinModule(
        sqlDriver = sqlDriver
    ) + module {
        // Override IO Dispatcher
        single<CoroutineDispatcher>(named("IOCoroutineDispatcher")) { Dispatchers.IO }
    }
}