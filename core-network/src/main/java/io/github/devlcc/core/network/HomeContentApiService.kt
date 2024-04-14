package io.github.devlcc.core.network

import io.github.devlcc.core.model.ChallengeDayOfTheWeek
import io.github.devlcc.core.network.dto.GetActivitiesResponse

interface HomeContentApiService {

    /**
     * [GET] /home
     */
    suspend fun getActivities(
        whichDay: ChallengeDayOfTheWeek
    ): GetActivitiesResponse

}