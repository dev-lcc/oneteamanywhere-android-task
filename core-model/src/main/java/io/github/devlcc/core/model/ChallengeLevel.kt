package io.github.devlcc.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class ChallengeLevel(
    val level: Int? = null,
    val dayOfTheWeek: ChallengeDayOfTheWeek? = null,
    val title: String? = null,
    val description: String? = null,
    val state: State? = null,
    val activities: List<ChallengeActivity> = listOf(),
): Parcelable {

    @Serializable
    @Parcelize
    enum class State(val value: String): Parcelable {
        @SerialName("AVAILABLE") Available("AVAILABLE"),
        @SerialName("LOCKED") Locked("LOCKED"),
        ;
    }

}
