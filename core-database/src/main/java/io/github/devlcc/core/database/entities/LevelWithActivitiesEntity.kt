package io.github.devlcc.core.database.entities

import io.github.devlcc.core.database.Activity
import io.github.devlcc.core.database.ActivityLevel

data class LevelWithActivitiesEntity(
    val level: ActivityLevel,
    val activities: List<Activity> = listOf(),
)