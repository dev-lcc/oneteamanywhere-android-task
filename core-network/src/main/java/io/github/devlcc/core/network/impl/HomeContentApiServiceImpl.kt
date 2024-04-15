package io.github.devlcc.core.network.impl

import io.github.devlcc.core.model.ChallengeDayOfTheWeek
import io.github.devlcc.core.network.HomeContentApiService
import io.github.devlcc.core.network.dto.GetActivitiesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.http.contentType

class HomeContentApiServiceImpl(
    // This can be different based on buildType OR buildFlavor
    private val restApiEndpoint: String = "https://private-905cf-otaandroidtask.apiary-mock.com",
    private val ktorClient: HttpClient,
): HomeContentApiService {

    override suspend fun getActivities(whichDay: ChallengeDayOfTheWeek): GetActivitiesResponse {
        try {
            val response = ktorClient.request(
                "${restApiEndpoint}/home"
            ) {
                method = HttpMethod.Get
                contentType(ContentType.Application.Json)
            }

            return response.body()
        } catch (
            // Multiple types of error(s) can be defined here (i.e. based on error code received, etc.)
            // OR generic network or server errors
            err: Throwable
        ) {
            err.printStackTrace()
            throw err
        }
    }
}