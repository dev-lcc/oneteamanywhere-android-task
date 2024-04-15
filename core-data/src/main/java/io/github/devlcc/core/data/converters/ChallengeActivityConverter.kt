package io.github.devlcc.core.data.converters

import io.github.devlcc.core.model.ChallengeActivity
import io.github.devlcc.core.model.ChallengeDayOfTheWeek
import io.github.devlcc.core.model.ChallengeLevel
import io.github.devlcc.core.network.dto.LevelDTO
import kotlinx.serialization.json.Json
import org.mobilenativefoundation.store.store5.Converter
import io.github.devlcc.core.database.Activity as ActivityEntity

//class ChallengeActivityConverter(
//    private val level: Int,
//    private val dayOfTheWeek: ChallengeDayOfTheWeek,
//    private val json: Json,
//) : Converter<LevelDTO.Activity, ActivityEntity, ChallengeActivity> {
//    override fun fromNetworkToLocal(network: LevelDTO.Activity): ActivityEntity {
//        return network.mapToEntity(
//            level = level,
//            dayOfTheWeek = dayOfTheWeek,
//            json = json,
//        )
//    }
//
//    override fun fromOutputToLocal(output: ChallengeActivity): ActivityEntity {
//        return output.mapToEntity(
//            level = level,
//            dayOfTheWeek = dayOfTheWeek,
//            json = json,
//        )
//    }
//}

fun LevelDTO.Activity.mapToEntity(
    level: LevelDTO,
    dayOfTheWeek: ChallengeDayOfTheWeek,
    json: Json,
) =
    ActivityEntity(
        id = this.id!!,
        challengeId = this.challengeID,
        type = this.type,
        title = this.title,
        titleB = this.titleB,
        description = this.description,
        descriptionB = this.descriptionB,
        state = this.state,
        icon = this.icon?.let { json.encodeToString(LevelDTO.Activity.Icon.serializer(), it) },
        lockedIcon = this.lockedIcon?.let {
            json.encodeToString(
                LevelDTO.Activity.Icon.serializer(),
                it
            )
        },
        level = level.level?.toLong(10) ?: 0L,
        levelTitle = level.title,
        levelDescription = level.description,
        levelState = level.state,
        dayOfTheWeek = dayOfTheWeek.value.toLong(),
    )

fun ChallengeActivity.mapToEntity(
    level: ChallengeLevel,
    dayOfTheWeek: ChallengeDayOfTheWeek,
    json: Json,
) =
    ActivityEntity(
        id = this.id!!,
        challengeId = this.challengeId,
        type = this.type?.value,
        title = this.title,
        titleB = this.titleB,
        description = this.description,
        descriptionB = this.descriptionB,
        state = this.state?.value,
        icon = this.icon?.let { json.encodeToString(ChallengeActivity.Icon.serializer(), it) },
        lockedIcon = this.lockedIcon?.let {
            json.encodeToString(
                ChallengeActivity.Icon.serializer(),
                it
            )
        },
        level = level.level?.toLong() ?: 0L,
        levelTitle = level.title,
        levelDescription = level.description,
        levelState = level.state?.value,
        dayOfTheWeek = dayOfTheWeek.value.toLong(),
    )

fun ActivityEntity.mapToModel(
    json: Json,
) =
    ChallengeActivity(
        id = this.id,
        challengeId = this.challengeId,
        type = this.type?.let { typeStr -> ChallengeActivity.Type.entries.find { it.value == typeStr } },
        title = this.title,
        titleB = this.titleB,
        description = this.description,
        descriptionB = this.descriptionB,
        state = this.state?.let { stateStr -> ChallengeActivity.State.entries.find { it.value == stateStr } },
        icon = this.icon?.let { json.decodeFromString(ChallengeActivity.Icon.serializer(), it) },
        lockedIcon = this.lockedIcon?.let {
            json.decodeFromString(
                ChallengeActivity.Icon.serializer(),
                it
            )
        },
    )

fun LevelDTO.Activity.mapToModel() =
    ChallengeActivity(
        id = this.id,
        challengeId = this.challengeID,
        type = this.type?.let { typeStr -> ChallengeActivity.Type.entries.find { it.value == typeStr } },
        title = this.title,
        titleB = this.titleB,
        description = this.description,
        descriptionB = this.descriptionB,
        state = this.state?.let { stateStr -> ChallengeActivity.State.entries.find { it.value == stateStr } },
        icon = this.icon?.let { icon ->
            ChallengeActivity.Icon(
                title = icon.title,
                description = icon.description,
                file = icon.file?.let { file ->
                    ChallengeActivity.Icon.File(
                        url = file.url,
                        fileName = file.fileName,
                        contentType = file.contentType,
                        details = file.details?.let { details ->
                            ChallengeActivity.Icon.File.Details(
                                size = details.size,
                            )
                        }
                    )
                }
            )
        },
        lockedIcon = this.lockedIcon?.let { lockedIcon ->
            ChallengeActivity.Icon(
                title = lockedIcon.title,
                description = lockedIcon.description,
                file = lockedIcon.file?.let { file ->
                    ChallengeActivity.Icon.File(
                        url = file.url,
                        fileName = file.fileName,
                        contentType = file.contentType,
                        details = file.details?.let { details ->
                            ChallengeActivity.Icon.File.Details(
                                size = details.size,
                            )
                        }
                    )
                }
            )
        },
    )
