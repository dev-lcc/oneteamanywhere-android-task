package io.github.devlcc.core.database.di

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import io.github.devlcc.core.database.ActivityDao
import io.github.devlcc.core.database.ActivityDaoImpl
import io.github.devlcc.core.database.AppDatabase
import io.github.devlcc.core.database.OTAAndroidTaskDatabase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.jetbrains.annotations.VisibleForTesting
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun getCoreDatabaseKoinModule(): Module = module {

    single<OTAAndroidTaskDatabase> {
        provideAppDatabase(
            sqlDriver = get(),
        )
    }

    single<ActivityDao> {
        ActivityDaoImpl(
            database = get(),
            ioCoroutineDispatcher = get(named("IOCoroutineDispatcher")),
        )
    }
    // TODO:: Define other DAO instances here...

}

private fun provideAppDatabase(
    sqlDriver: SqlDriver
) = AppDatabase.create(sqlDriver = sqlDriver)

@VisibleForTesting
fun testCoreDatabaseKoinModule(): List<Module> {
    return getCoreDatabaseKoinModule() + module {

        single<SqlDriver> {
            provideInMemorySqlDriver()
        }

        // Override IO Dispatcher
        single<CoroutineDispatcher>(named("IOCoroutineDispatcher")) { Dispatchers.IO }
    }
}

internal fun provideInMemorySqlDriver(): SqlDriver =
    JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        .also {
            OTAAndroidTaskDatabase.Schema.create(it)
        }