package io.github.devlcc.core.database

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import io.github.devlcc.core.database.entities.LevelWithActivitiesEntity
import io.github.devlcc.core.model.ChallengeDayOfTheWeek
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.withContext

class ActivityDaoImpl(
    private val database: OTAAndroidTaskDatabase,
    private val ioCoroutineDispatcher: CoroutineDispatcher,
): ActivityDao {

    private val activityQueries: ActivityQueries = database.activityQueries
    private val activityLevelQueries: ActivityLevelQueries = database.activityLevelQueries

    override suspend fun getAllActivities(): List<LevelWithActivitiesEntity> {
        val allLevels = withContext(ioCoroutineDispatcher) {
            activityLevelQueries.getActivityLevels()
                .executeAsList()
        }
        val allActivities = withContext(ioCoroutineDispatcher) {
            activityQueries.getAllActivities()
                .executeAsList()
        }

        return allLevels.associateWith { key -> allActivities.filter { activity -> activity.level == key.level } }
            .map { (key, value) ->
                LevelWithActivitiesEntity(key, value)
            }
    }

    override fun getAllActivitiesStream(): Flow<List<LevelWithActivitiesEntity>> {
        return combine(
            activityLevelQueries.getActivityLevels().asFlow().mapToList(ioCoroutineDispatcher),
            activityQueries.getAllActivities().asFlow().mapToList(ioCoroutineDispatcher)
        ) { allLevels, allActivities ->
            allLevels.associateWith { key -> allActivities.filter { activity -> activity.level == key.level } }
                .map { (key, value) ->
                    LevelWithActivitiesEntity(key, value)
                }
        }
    }

    override suspend fun getActivityById(id: String): Activity {
        return withContext(ioCoroutineDispatcher) {
            activityQueries.getActivityById(id).executeAsOneOrNull() ?: throw IndexOutOfBoundsException("Activity with ID=$id not found...")
        }
    }

    override suspend fun getActivityByIdStream(id: String): Flow<Activity> {
        return activityQueries.getActivityById(id)
            .asFlow()
            .mapToOne(ioCoroutineDispatcher)
    }

    override suspend fun upsert(vararg levels: LevelWithActivitiesEntity) {

        database.transaction {
            levels.forEach { (level, activities) ->
                activityLevelQueries.upsertLevel(
                    level = level.level,
                    title = level.title,
                    description = level.description,
                    state = level.state,
                    dayOfTheWeek = level.dayOfTheWeek,
                )

                activities.forEach { activity ->
                    activityQueries.upsertActivity(
                        id = activity.id,
                        challengeId = activity.challengeId,
                        type = activity.type,
                        title = activity.title,
                        titleB = activity.titleB,
                        description = activity.description,
                        descriptionB = activity.descriptionB,
                        state = activity.state,
                        icon = activity.icon,
                        lockedIcon = activity.lockedIcon,
                        level = activity.level,
                        dayOfTheWeek = activity.dayOfTheWeek,
                    )
                }
            }
        }
    }

    override fun removeActivityById(id: String) {
        database.transaction {
            activityQueries.removeById(id)
        }
    }

    override fun removeActivities(day: Int, level: Int?) {
        database.transaction {
            if(level != null) {
                activityLevelQueries.removeByDayAndLevel(dayOfTheWeek = day.toLong(), level = level.toLong())
                activityQueries.removeByDayAndLevel(dayOfTheWeek = day.toLong(), level = level.toLong())
            } else {
                activityLevelQueries.removeByDay(dayOfTheWeek = day.toLong())
                activityQueries.removeByDay(dayOfTheWeek = day.toLong())
            }
        }
    }

    override fun removeAll() {
        database.transaction {
            activityLevelQueries.removeAll()
            activityQueries.removeAll()
        }
    }
}