package io.github.devlcc.core.database

import io.github.devlcc.core.database.entities.LevelWithActivitiesEntity
import kotlinx.coroutines.flow.Flow

interface ActivityDao {

    suspend fun getAllActivities(): List<LevelWithActivitiesEntity>

    fun getAllActivitiesStream(): Flow<List<LevelWithActivitiesEntity>>

    suspend fun getActivitiesByDay(dayOfTheWeek: Int): List<LevelWithActivitiesEntity>

    fun getActivitiesByDayStream(dayOfTheWeek: Int): Flow<List<LevelWithActivitiesEntity>>

    suspend fun getActivityById(
        id: String
    ): Activity

    suspend fun getActivityByIdStream(
        id: String
    ): Flow<Activity>

    suspend fun upsert(vararg levels: LevelWithActivitiesEntity)

    fun removeActivityById(id: String)

    fun removeActivities(day: Int/*ChallengeDayOfTheWeek*/, level: Int? = null)

    fun removeAll()

}
