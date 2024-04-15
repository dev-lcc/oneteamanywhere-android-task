package io.github.devlcc.core.database

import kotlinx.coroutines.flow.Flow

interface ActivityDao {

    suspend fun getAllActivities(): List<Activity/*LevelWithActivitiesEntity*/>

    fun getAllActivitiesStream(): Flow<List<Activity/*LevelWithActivitiesEntity*/>>

    suspend fun getActivitiesByDay(dayOfTheWeek: Int): List<Activity/*LevelWithActivitiesEntity*/>

    fun getActivitiesByDayStream(dayOfTheWeek: Int): Flow<List<Activity/*LevelWithActivitiesEntity*/>>

    suspend fun getActivityById(
        id: String
    ): Activity

    suspend fun getActivityByIdStream(
        id: String
    ): Flow<Activity>

    suspend fun upsert(vararg activities/*levels*/: Activity/*LevelWithActivitiesEntity*/)

    fun removeActivityById(id: String)

    fun removeActivities(day: Int/*ChallengeDayOfTheWeek*/, level: Int? = null)

    fun removeAll()

}
