package io.github.devlcc.core.data

import io.github.devlcc.core.data.converters.mapToEntity
import io.github.devlcc.core.data.converters.mapToModel
import io.github.devlcc.core.database.Activity
import io.github.devlcc.core.database.ActivityDao
import io.github.devlcc.core.model.ChallengeDayOfTheWeek
import io.github.devlcc.core.model.ChallengeLevel
import io.github.devlcc.core.network.HomeContentApiService
import io.github.devlcc.core.network.dto.LevelDTO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.MemoryPolicy
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.StoreBuilder
import kotlin.time.Duration.Companion.minutes

/**
 * TODO:: For some reasons, I encountered some issues with my persistence(LocalDatabase) implementation
 * where it currently behaves as if it emits data infinitely.
 *  - So I decided to comment it out for now, and place a temporary in-memory persistence via StateFlow.
 */
class ActivityRepository(
    private val activityApiService: HomeContentApiService,
    private val activityLocalDao: ActivityDao,
    private val json: Json,
) {

    private val _challengeLevels = MutableStateFlow(listOf<ChallengeLevel>())
    fun challengeLevels(fromWhichDay: ChallengeDayOfTheWeek): Flow<List<ChallengeLevel>> =
//        activityLocalDao.getAllActivitiesStream()
//            /*getActivitiesByDayStream(dayOfTheWeek = fromWhichDay.value)*/
//            .map { activities ->
//                mutableListOf<ChallengeLevel>().apply {
//                    activities.forEach { activity ->
//                        val level = activity.level.toInt()
//                        add(
//                            ChallengeLevel(
//                                level = level,
//                                dayOfTheWeek = fromWhichDay,
//                                title = activity.levelTitle,
//                                description = activity.levelDescription,
//                                state = activity.levelState?.let { levelState ->
//                                    ChallengeLevel.State.entries.find { it.value == levelState }
//                                },
//                                activities = activities
//                                    .filter {
//                                        it.level == level.toLong()
//                                                && it.dayOfTheWeek == fromWhichDay.value.toLong()
//                                    }.map { it.mapToModel(json) },
//                            )
//                        )
//                    }
//                }.distinctBy { it.dayOfTheWeek }
//            }
//            .distinctUntilChanged()
        _challengeLevels.asStateFlow()

    suspend fun fresh(fromWhichDay: ChallengeDayOfTheWeek) {
        val response = withContext(Dispatchers.IO) {
            activityApiService.getActivities(fromWhichDay)
        }
        _challengeLevels.update {
            response.levels.map { level ->
                ChallengeLevel(
                    level = level.level?.toInt(10),
                    dayOfTheWeek = fromWhichDay,
                    title = level.title,
                    description = level.description,
                    state = level.state?.let { levelState ->
                        ChallengeLevel.State.entries.find { it.value == levelState }
                    },
                    activities = level.activities.map { it.mapToModel() },
                )
            }
        }
//        activityLocalDao.upsert(
//            *(
//                response.levels.fold(listOf<Activity>()) { accumulated, level ->
//                    ArrayList(accumulated).apply {
//                        addAll(
//                            level.activities.map { activityDTO ->
//                                activityDTO.mapToEntity(
//                                    level = level,
//                                    dayOfTheWeek = fromWhichDay,
//                                    json = json,
//                                )
//                            }
//                        )
//                    }
//                }
//                    .distinct()
//            ).toTypedArray()
//        )
    }

    private val store = StoreBuilder
        .from<ChallengeDayOfTheWeek, List<LevelDTO>, List<ChallengeLevel>>(
            fetcher = Fetcher.ofFlow {
                flow {
                    emit(
                        activityApiService.getActivities(it).levels,
                    )
                }
            },
            sourceOfTruth = SourceOfTruth.Companion.of(
                reader = { key: ChallengeDayOfTheWeek ->
                    activityLocalDao.getActivitiesByDayStream(dayOfTheWeek = key.value)
                        .map { activities ->
                            mutableListOf<ChallengeLevel>().apply {
                                activities.forEach { activity ->
                                    val level = activity.level.toInt()
                                    add(
                                        ChallengeLevel(
                                            level = level,
                                            dayOfTheWeek = key,
                                            title = activity.levelTitle,
                                            description = activity.levelDescription,
                                            state = activity.levelState?.let { levelState ->
                                                ChallengeLevel.State.entries.find { it.value == levelState }
                                            },
                                            activities = activities
                                                .filter {
                                                    it.level == level.toLong()
                                                            && it.dayOfTheWeek == key.value.toLong()
                                                }.map { it.mapToModel(json) },
                                        )
                                    )
                                }
                            }.distinct()
                        }
                },
                writer = { key, entity ->
                    activityLocalDao.upsert(
                        *(
                                entity.fold(listOf<Activity>()) { accumulated, level ->
                                    ArrayList(accumulated).apply {
                                        addAll(
                                            level.activities.map { activityDTO ->
                                                activityDTO.mapToEntity(
                                                    level = level,
                                                    dayOfTheWeek = key,
                                                    json = json,
                                                )
                                            }
                                        )
                                    }
                                }
                        ).toTypedArray()
                    )
                },
                delete = { key ->
                    activityLocalDao.removeActivities(day = key.value)
                },
                deleteAll = { activityLocalDao.removeAll() }
            )
        )
        .cachePolicy(
            MemoryPolicy.builder<Any, Any>()
                .setMaxSize(1024)
                .setExpireAfterAccess(10.minutes)
                .build()
        )
        .build()

    fun store(): Store<ChallengeDayOfTheWeek, List<ChallengeLevel>> = this.store
}