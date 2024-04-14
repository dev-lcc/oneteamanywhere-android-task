package io.github.devlcc.core.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LevelDTO(
    val level: String? = null,
    val title: String? = null,
    val description: String? = null,
    val state: String? = null,
    val activities: List<Activity> = listOf(),
) {

    @Serializable
    data class Activity(
        val id: String? = null,
        @SerialName("challengeId")
        val challengeID: String? = null,
        val type: String? = null,   // "COMMIT" | "PRACTICE" | "RECAP"
        val title: String? = null,
        val titleB: String? = null,
        val description: String? = null,
        val descriptionB: String? = null,
        val state: String? = null,   // "NOT_SET" | "COMPLETED"
        val icon: Icon? = null,
        val lockedIcon: Icon? = null
    ) {
        @Serializable
        data class Icon (
            val file: File? = null,
            val title: String? = null,
            val description: String? = null
        ) {

            @Serializable
            data class File (
                val url: String? = null,
                val details: Details? = null,
                val fileName: String? = null,
                val contentType: String? = null,    // ex. "application/pdf", etc.
            ) {

                @Serializable
                data class Details (
                    val size: Long? = null
                )
            }
        }
    }

}
