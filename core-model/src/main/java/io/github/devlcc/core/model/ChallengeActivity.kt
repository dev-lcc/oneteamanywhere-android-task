package io.github.devlcc.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Parcelize
data class ChallengeActivity(
    val id: String? = null,
    val challengeId: String? = null,
    val type: Type? = null,   // "COMMIT" | "PRACTICE" | "RECAP"
    val title: String? = null,
    val titleB: String? = null,
    val description: String? = null,
    val descriptionB: String? = null,
    val state: State? = null,   // "NOT_SET" | "LOCKED"
    val icon: Icon? = null,
    val lockedIcon: Icon? = null
): Parcelable {

    @Serializable
    @Parcelize
    enum class Type(val value: String): Parcelable {
        @SerialName("COMMIT") Commit("COMMIT"),
        @SerialName("PRACTICE") Practice("PRACTICE"),
        @SerialName("RECAP") Recap("RECAP");
    }

    @Serializable
    @Parcelize
    enum class State(val value: String): Parcelable {
        @SerialName("NOT_SET") NotSet("NOT_SET"),
        @SerialName("COMPLETED") Completed("COMPLETED"),
        ;
    }

    @Serializable
    @Parcelize
    data class Icon (
        val file: File? = null,
        val title: String? = null,
        val description: String? = null
    ): Parcelable {

        @Serializable
        @Parcelize
        data class File (
            val url: String? = null,
            val details: Details? = null,
            val fileName: String? = null,
            val contentType: String? = null,    // ex. "application/pdf", etc.
        ): Parcelable {

            @Serializable
            @Parcelize
            data class Details(
                val size: Long? = null
            ): Parcelable
        }
    }
}
