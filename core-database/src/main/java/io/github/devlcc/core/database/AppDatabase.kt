package io.github.devlcc.core.database

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

object AppDatabase {

    fun create(sqlDriver: SqlDriver): OTAAndroidTaskDatabase {
        val database = OTAAndroidTaskDatabase(sqlDriver)

        // TODO:: Do more work with the database(i.e. DB migration, constants, etc.)

        return database
    }

    const val DB_NAME = "OTAAndroidTaskDatabase.db"
}

fun AppDatabase.getAndroidSqliteDriver(context: Context) =
    AndroidSqliteDriver(OTAAndroidTaskDatabase.Schema, context, DB_NAME)