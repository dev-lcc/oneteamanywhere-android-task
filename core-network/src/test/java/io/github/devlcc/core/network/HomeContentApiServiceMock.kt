package io.github.devlcc.core.network

import io.github.devlcc.core.network.fake.FakeHomeContentApiService
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.headersOf
import io.ktor.utils.io.ByteReadChannel
import org.jetbrains.annotations.VisibleForTesting

@VisibleForTesting
object HomeContentApiServiceMock {

    object GetActivities {

        fun mockEngineStatusSuccess() = MockEngine { request ->
            respond(
                content = ByteReadChannel(success()),
                status = HttpStatusCode.OK,
                headers = headersOf(
                    HttpHeaders.ContentType, "application/json"
                )
            )
        }

        fun mockEngineStatusError(
            status: HttpStatusCode = HttpStatusCode.InternalServerError
        ) = MockEngine { _/*request*/ ->
            respond(
                content = ByteReadChannel.Empty,
                status = HttpStatusCode.InternalServerError,
                headers = headersOf(
                    HttpHeaders.ContentType, "application/json"
                )
            )
        }

        fun success() = FakeHomeContentApiService.JsonContent

    }

    // TODO:: Define Other Mock API responses here...

}