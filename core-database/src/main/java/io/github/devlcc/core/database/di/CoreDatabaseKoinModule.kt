package io.github.devlcc.core.database.di

import app.cash.sqldelight.db.SqlDriver
import io.github.devlcc.core.database.ActivityDao
import io.github.devlcc.core.database.ActivityDaoImpl
import io.github.devlcc.core.database.AppDatabase
import io.github.devlcc.core.database.OTAAndroidTaskDatabase
import org.koin.core.module.Module
import org.koin.core.qualifier.named
import org.koin.dsl.module

fun getCoreDatabaseKoinModule(
    sqlDriver: SqlDriver,   // AndroidSqliteDriver(OTAAndroidTaskDatabase.Schema, context, AppDatabase.DB_NAME)
): Module = module {

    single<OTAAndroidTaskDatabase> {
        provideAppDatabase(
            sqlDriver = sqlDriver,
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