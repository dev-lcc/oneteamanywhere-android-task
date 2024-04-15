package io.github.devlcc.ota.ui.presentation.home

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.devlcc.core.data.ActivityRepository
import io.github.devlcc.core.data.UserRepository
import io.github.devlcc.core.model.ChallengeDayOfTheWeek
import io.github.devlcc.core.model.ChallengeLevel
import io.github.devlcc.core.model.User
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.mobilenativefoundation.store.store5.StoreReadRequest
import org.mobilenativefoundation.store.store5.StoreReadResponse
import org.mobilenativefoundation.store.store5.impl.extensions.fresh

class HomeScreenViewModel(
    /*private val handle: SavedStateHandle,*/
    private val userRepository: UserRepository,
    private val activityRepository: ActivityRepository,
) : ViewModel() {

    /*private val isLoading: StateFlow<Boolean> =
        handle.getStateFlow(SavedStateHandleKey.IsLoading, true)*/

    /*private val selectedDay: StateFlow<ChallengeDayOfTheWeek> =
        handle.getStateFlow(SavedStateHandleKey.SelectedDay, ChallengeDayOfTheWeek.Monday)*/
    private val selectedDay = MutableStateFlow(ChallengeDayOfTheWeek.Monday)

    @Suppress("UNCHECKED_CAST", "UNUSED_CHANGED_VALUE")
    val uiState: StateFlow<HomeScreenUiState>
        get() =
            combine(
                /*isLoading,*/
                selectedDay,
                userRepository.user,
                activityRepository.challengeLevels(
                    fromWhichDay = selectedDay.value,
                )
            /*.store()
                    .stream(
                        request = StoreReadRequest.freshWithFallBackToSourceOfTruth(
                            key = selectedDay.value,
                        )
                    )*/
//                    .onEach {
//                        // HIDE Progress Indicator
//                        handle[SavedStateHandleKey.IsLoading] = false
//                    }
            ) { values ->
                var index = 0
                /*val _isLoading = values[index++] as Boolean*/
                val _selectedDay = values[index++] as ChallengeDayOfTheWeek
                val _user = values[index++] as User
                val _challengeLevels = values[index++] as List<ChallengeLevel>
//                val activitiesStoreValue =
//                    values[index++] as StoreReadResponse<List<ChallengeLevel>>
//
//                val _isLoading = activitiesStoreValue/*.dataOrNull() == null*/ is StoreReadResponse.Loading
                val _isLoading = _challengeLevels.isEmpty()

                val journeyStatus = _user.journeyStatus
                val firePoints = _user.firePoints ?: 0
                val content = (_challengeLevels/*activitiesStoreValue.dataOrNull() ?: listOf()*/)
                    .let {
                        if(it.isEmpty()) HomeScreenUiState.HomeContent.Empty
                        else HomeScreenUiState.HomeContent.Items(it)
                    }

                HomeScreenUiState(
                    isLoading = _isLoading,
                    journeyStatus = journeyStatus,
                    firePoints = firePoints,
                    selectedDay = _selectedDay,
                    content = content,
                )
            }
                .stateIn(
                    scope = viewModelScope,
                    started = SharingStarted.WhileSubscribed(5000L),
                    initialValue = HomeScreenUiState(
                        isLoading = true,
                    )
                )

    private val _effect = MutableSharedFlow<HomeScreenEffect>()
    val effect: SharedFlow<HomeScreenEffect> = _effect.asSharedFlow()

    init {
        viewModelScope.launch {
            userRepository.fresh()
            activityRepository.fresh(fromWhichDay = selectedDay.value)
        }
    }

    fun handle(event: HomeScreenEvent) {
        viewModelScope.launch {
            when (event) {
                is HomeScreenEvent.NavigateToMyAccount -> {
                    _effect.emit(HomeScreenEffect.NavigateToMyAccount)
                }

                is HomeScreenEvent.ToggleSelectedDay -> {
                    // HIDE Progress Indicator
                    /*handle[SavedStateHandleKey.SelectedDay] = event.which*/
                    selectedDay.update { event.which }
                    /*activityRepository.store().fresh(key = event.which)*/
                    try {
                        activityRepository.fresh(fromWhichDay = event.which)
                    } catch (_: Throwable) {
                        _effect.emit(HomeScreenEffect.ErrorFetchActivities)
                    }
                }

                is HomeScreenEvent.Refresh -> {
                    val currentDaySelected = selectedDay.value
                    // DISPLAY Progress Indicator
                    /*handle[SavedStateHandleKey.IsLoading] = true*/
                    /*activityRepository.store().fresh(key = currentDaySelected)*/
                    try {
                        activityRepository.fresh(fromWhichDay = currentDaySelected)
                    } catch (_: Throwable) {
                        _effect.emit(HomeScreenEffect.ErrorFetchActivities)
                    }
                }

                is HomeScreenEvent.NavigateToActivity -> {
                    _effect.emit(HomeScreenEffect.NavigateToActivity(which = event.which))
                }

                is HomeScreenEvent.NavigateToJourney -> {
                    _effect.emit(HomeScreenEffect.NavigateToJourney)
                }
            }
        }
    }

    object SavedStateHandleKey {
        const val SelectedDay = "SelectedDay"
        const val IsLoading = "IsLoading"
    }

}