package io.github.devlcc.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class GetActivitiesResponse(
    val levels: List<LevelDTO> = listOf(),
)
