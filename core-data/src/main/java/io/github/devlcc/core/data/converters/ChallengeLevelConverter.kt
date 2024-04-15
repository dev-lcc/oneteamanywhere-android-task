package io.github.devlcc.core.data.converters

//import io.github.devlcc.core.database.ActivityLevel
import io.github.devlcc.core.model.ChallengeDayOfTheWeek
import io.github.devlcc.core.model.ChallengeLevel
import io.github.devlcc.core.network.dto.LevelDTO
import org.mobilenativefoundation.store.store5.Converter
//import io.github.devlcc.core.database.ActivityLevel as ActivityLevelEntity

//class ChallengeLevelConverter(
//    private val dayOfTheWeek: ChallengeDayOfTheWeek,
//) : Converter<LevelDTO, ActivityLevelEntity, ChallengeLevel> {
//    override fun fromNetworkToLocal(network: LevelDTO): ActivityLevel {
//        return network.mapToEntity(
//            dayOfTheWeek = dayOfTheWeek,
//        )
//    }
//
//    override fun fromOutputToLocal(output: ChallengeLevel): ActivityLevel {
//        return output.mapToEntity(
//            dayOfTheWeek = dayOfTheWeek,
//        )
//    }
//}

//fun LevelDTO.mapToEntity(
//    dayOfTheWeek: ChallengeDayOfTheWeek,
//) =
//    ActivityLevelEntity(
//        level = this.level?.toLong() ?: 0L,
//        title = this.title,
//        description = this.description,
//        state = this.state,
//        dayOfTheWeek = dayOfTheWeek.value.toLong(),
//    )
//
//fun ChallengeLevel.mapToEntity(
//    dayOfTheWeek: ChallengeDayOfTheWeek,
//) =
//    ActivityLevelEntity(
//        level = this.level?.toLong() ?: 0L,
//        title = this.title,
//        description = this.description,
//        state = this.state?.value,
//        dayOfTheWeek = dayOfTheWeek.value.toLong(),
//    )
//
//fun ActivityLevelEntity.mapToModel(
//    dayOfTheWeek: ChallengeDayOfTheWeek,
//) =
//    ChallengeLevel(
//        level = this.level.toInt(),
//        dayOfTheWeek = dayOfTheWeek,
//        title = this.title,
//        description = this.description,
//        state = this.state?.let { st -> ChallengeLevel.State.entries.find { it.value == st } },
//    )

