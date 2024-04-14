package io.github.devlcc.core.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChallengeActivity(
    val id: String? = null,
    val challengeID: String? = null,
    val type: Type? = null,   // "COMMIT" | "PRACTICE" | "RECAP"
    val title: String? = null,
    val titleB: String? = null,
    val description: String? = null,
    val descriptionB: String? = null,
    val state: State? = null,   // "NOT_SET" | "LOCKED"
    val icon: Icon? = null,
    val lockedIcon: Icon? = null
): Parcelable {

    @Parcelize
    enum class Type(val value: String): Parcelable {
        Commit("COMMIT"),
        Practice("PRACTICE"),
        Recap("RECAP");
    }

    @Parcelize
    enum class State(val value: String): Parcelable {
        NotSet("NOT_SET"),
        Locked("LOCKED"),
        ;
    }

    @Parcelize
    data class Icon (
        val file: File? = null,
        val title: String? = null,
        val description: String? = null
    ): Parcelable {

        @Parcelize
        data class File (
            val url: String? = null,
            val details: Details? = null,
            val fileName: String? = null,
            val contentType: String? = null,    // ex. "application/pdf", etc.
        ): Parcelable {

            @Parcelize
            data class Details(
                val size: Long? = null
            ): Parcelable
        }
    }
}
