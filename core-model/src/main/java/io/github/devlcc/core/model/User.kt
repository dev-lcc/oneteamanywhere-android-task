package io.github.devlcc.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class User(
    val id: String? = null,
    val profileIcon: String? = null,
    val journeyStatus: JourneyStatus? = null,
    val firePoints: Int? = null,
): Parcelable {

    @Serializable
    @Parcelize
    data class JourneyStatus(
        val progress: Int, // 0-100
    ): Parcelable {
        val state: State
            get() = when(progress) {
                in 0..20 -> State.Angry
                in 21..40 -> State.TamingTemper
                in 41..60 -> State.Calm
                in 61..80 -> State.Interested
                else/*in 81..100*/ -> State.Happy
            }

        @Serializable
        @Parcelize
        enum class State: Parcelable {
            @SerialName("Angry") Angry,  // 0-20
            @SerialName("TamingTemper") TamingTemper,   // 21-40
            @SerialName("Calm") Calm,   // 41-60
            @SerialName("Interested") Interested, // 61-80
            @SerialName("Happy") Happy,  // 81-100
        }
    }
}