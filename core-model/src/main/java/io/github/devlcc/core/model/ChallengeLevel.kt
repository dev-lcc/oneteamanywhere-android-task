package io.github.devlcc.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChallengeLevel(
    val level: String? = null,
    val title: String? = null,
    val description: String? = null,
    val state: String? = null,
    val activities: List<ChallengeActivity> = listOf(),
): Parcelable
