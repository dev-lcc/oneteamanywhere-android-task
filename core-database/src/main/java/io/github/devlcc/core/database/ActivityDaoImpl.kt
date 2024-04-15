package io.github.devlcc.core.database

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.coroutines.mapToOne
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.withContext

class ActivityDaoImpl(
    private val database: OTAAndroidTaskDatabase,
    private val ioCoroutineDispatcher: CoroutineDispatcher,
): ActivityDao {

    private val activityQueries: ActivityQueries = database.activityQueries

    override suspend fun getAllActivities(): List<Activity/*LevelWithActivitiesEntity*/> {
        /*val allLevels = withContext(ioCoroutineDispatcher) {
            activityLevelQueries.getActivityLevels()
                .executeAsList()
        }*/
        val allActivities = withContext(ioCoroutineDispatcher) {
            activityQueries.getAllActivities()
                .executeAsList()
        }

        return allActivities/*allLevels.associateWith { key -> allActivities.filter { activity -> activity.level == key.level } }
            .map { (key, value) ->
                LevelWithActivitiesEntity(key, value)
            }*/
    }

    override fun getAllActivitiesStream(): Flow<List<Activity/*LevelWithActivitiesEntity*/>> {
        return activityQueries.getAllActivities().asFlow().mapToList(ioCoroutineDispatcher)

//        return combine(
//            activityLevelQueries.getActivityLevels().asFlow().mapToList(ioCoroutineDispatcher),
//            activityQueries.getAllActivities().asFlow().mapToList(ioCoroutineDispatcher)
//        ) { allLevels, allActivities ->
//            allLevels.associateWith { key -> allActivities.filter { activity -> activity.level == key.level } }
//                .map { (key, value) ->
//                    LevelWithActivitiesEntity(key, value)
//                }
//        }
//            .distinctUntilChanged()
    }

    override suspend fun getActivitiesByDay(dayOfTheWeek: Int): List<Activity/*LevelWithActivitiesEntity*/> {
//        val levelsByDay = withContext(ioCoroutineDispatcher) {
//            activityLevelQueries.getActivityLevelsByDay(dayOfTheWeek = dayOfTheWeek.toLong())
//                .executeAsList()
//        }
        val activitiesByDay = withContext(ioCoroutineDispatcher) {
            activityQueries.getActivitiesByDay(dayOfTheWeek = dayOfTheWeek.toLong())
                .executeAsList()
        }

//        return levelsByDay.associateWith { key -> activitiesByDay.filter { activity -> activity.level == key.level } }
//            .map { (key, value) ->
//                LevelWithActivitiesEntity(key, value)
//            }
        return activitiesByDay
    }

    override fun getActivitiesByDayStream(dayOfTheWeek: Int): Flow<List<Activity/*LevelWithActivitiesEntity*/>> {
//        return combine(
//            activityLevelQueries.getActivityLevelsByDay(dayOfTheWeek = dayOfTheWeek.toLong()).asFlow().mapToList(ioCoroutineDispatcher),
//            activityQueries.getActivitiesByDay(dayOfTheWeek = dayOfTheWeek.toLong()).asFlow().mapToList(ioCoroutineDispatcher)
//        ) { levelsByDay, activitiesByDay ->
//            levelsByDay.associateWith { key -> activitiesByDay.filter { activity -> activity.level == key.level } }
//                .map { (key, value) ->
//                    LevelWithActivitiesEntity(key, value)
//                }
//        }
//            .distinctUntilChanged()

        return activityQueries.getActivitiesByDay(dayOfTheWeek = dayOfTheWeek.toLong())
            .asFlow()
            .mapToList(ioCoroutineDispatcher)
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

    override suspend fun upsert(vararg activities: Activity/*LevelWithActivitiesEntity*/) {

        database.transaction {
//            levels.forEach { (level, activities) ->
//                activityLevelQueries.upsertLevel(
//                    level = level.level,
//                    title = level.title,
//                    description = level.description,
//                    state = level.state,
//                    dayOfTheWeek = level.dayOfTheWeek,
//                )
//
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
                        levelTitle = activity.levelTitle,
                        levelDescription = activity.levelDescription,
                        levelState = activity.levelState,
                        dayOfTheWeek = activity.dayOfTheWeek,
                    )
                }
//            }
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
//                activityLevelQueries.removeByDayAndLevel(dayOfTheWeek = day.toLong(), level = level.toLong())
                activityQueries.removeByDayAndLevel(dayOfTheWeek = day.toLong(), level = level.toLong())
            } else {
//                activityLevelQueries.removeByDay(dayOfTheWeek = day.toLong())
                activityQueries.removeByDay(dayOfTheWeek = day.toLong())
            }
        }
    }

    override fun removeAll() {
        database.transaction {
//            activityLevelQueries.removeAll()
            activityQueries.removeAll()
        }
    }
}