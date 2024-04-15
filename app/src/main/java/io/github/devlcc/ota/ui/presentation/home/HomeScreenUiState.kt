package io.github.devlcc.ota.ui.presentation.home

import io.github.devlcc.core.model.ChallengeDayOfTheWeek
import io.github.devlcc.core.model.ChallengeLevel
import io.github.devlcc.core.model.User

data class HomeScreenUiState(
    val isLoading: Boolean = true,
    val journeyStatus: User.JourneyStatus? = null,
    val firePoints: Int = 0,
    val selectedDay: ChallengeDayOfTheWeek = ChallengeDayOfTheWeek.Monday,
    val content: HomeContent = HomeContent.Empty,
) {
    sealed class HomeContent {
        data object Empty: HomeContent()
        data class Items(val data: List<ChallengeLevel>): HomeContent()
    }
}