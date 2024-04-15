package io.github.devlcc.core.data

import io.github.devlcc.core.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.UUID
import kotlin.random.Random

/**
 * TODO:: Mock/Hardcode User Data for now.
 *  - https://getahead.notion.site/Android-Task-a3a6336b9274434fb323bc204ba4219c
 */
class UserRepository {
    private val _user = MutableStateFlow(generateRandomUserData())
    val user: StateFlow<User> = _user.asStateFlow()

    suspend fun fresh() {
        _user.update { generateRandomUserData() }
    }
}

internal fun UserRepository.generateRandomUserData(): User =
    User(
        id = UUID.randomUUID().toString(),
        journeyStatus = User.JourneyStatus(
            progress = Random.nextInt(100),
        ),
        firePoints = Random.nextInt(300),
    )