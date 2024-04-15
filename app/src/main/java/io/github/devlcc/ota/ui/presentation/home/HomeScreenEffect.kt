package io.github.devlcc.ota.ui.presentation.home

import io.github.devlcc.core.model.ChallengeActivity

sealed class HomeScreenEffect {

    /**
     * Dispatched when error encountered on attempt fetch levels and activities.
     */
    data object ErrorFetchActivities: HomeScreenEffect()

    /**
     * Prompt navigate to `My Account` screen
     */
    data object NavigateToMyAccount: HomeScreenEffect()

    /**
     * Prompt navigate to `Activity Details` screen
     */
    data class NavigateToActivity(val which: ChallengeActivity): HomeScreenEffect()

    /**
     * Prompt navigate to `Journey` screen
     */
    data object NavigateToJourney: HomeScreenEffect()

}