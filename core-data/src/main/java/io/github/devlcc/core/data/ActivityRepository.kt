package io.github.devlcc.core.data

import io.github.devlcc.core.data.converters.mapToEntity
import io.github.devlcc.core.data.converters.mapToModel
import io.github.devlcc.core.database.ActivityDao
import io.github.devlcc.core.database.entities.LevelWithActivitiesEntity
import io.github.devlcc.core.model.ChallengeDayOfTheWeek
import io.github.devlcc.core.model.ChallengeLevel
import io.github.devlcc.core.network.HomeContentApiService
import io.github.devlcc.core.network.dto.LevelDTO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import org.mobilenativefoundation.store.store5.Fetcher
import org.mobilenativefoundation.store.store5.MemoryPolicy
import org.mobilenativefoundation.store.store5.SourceOfTruth
import org.mobilenativefoundation.store.store5.Store
import org.mobilenativefoundation.store.store5.StoreBuilder
import kotlin.time.Duration.Companion.minutes

class ActivityRepository(
    private val activityApiService: HomeContentApiService,
    private val activityLocalDao: ActivityDao,
    private val json: Json,
) {
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
                        .map { levelWithActivities ->
                            levelWithActivities.map { (level, activites) ->
                                level.mapToModel(
                                    dayOfTheWeek = key
                                ).copy(
                                    activities = activites.map { it.mapToModel(json) },
                                )
                            }
                        }
                },
                writer = { key, entity ->
                    activityLocalDao.upsert(
                        *(
                            entity.map { levelDTO ->
                                LevelWithActivitiesEntity(
                                    level = levelDTO.mapToEntity(dayOfTheWeek = key),
                                    activities = levelDTO.activities.map { activity ->
                                        activity.mapToEntity(
                                            level = levelDTO.level?.toInt(10)!!,
                                            dayOfTheWeek = key,
                                            json = json,
                                        )
                                    }
                                )

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
                .setMaxSize(10)
                .setExpireAfterAccess(10.minutes)
                .build()
        )
        .build()

    fun store(): Store<ChallengeDayOfTheWeek, List<ChallengeLevel>> = this.store
}