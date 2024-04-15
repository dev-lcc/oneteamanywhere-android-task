package io.github.devlcc.ota.ui.presentation.home

import io.github.devlcc.core.model.ChallengeActivity
import io.github.devlcc.core.model.ChallengeDayOfTheWeek

sealed class HomeScreenEvent {
    /**
     * On tap `My Account` action
     */
    data object NavigateToMyAccount : HomeScreenEvent()

    /**
     * On toggle selected day
     */
    data class ToggleSelectedDay(val which: ChallengeDayOfTheWeek) : HomeScreenEvent()

    /**
     * Fetched Challenge Levels and Activities
     *  - Invoked on swipe refresh
     */
    data object Refresh : HomeScreenEvent()

    /**
     * On select activity
     */
    data class NavigateToActivity(val which: ChallengeActivity) : HomeScreenEvent()

    /**
     * On tap `Journey` action
     */
    data object NavigateToJourney : HomeScreenEvent()

}