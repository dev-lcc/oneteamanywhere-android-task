package io.github.devlcc.core.network.di

import io.github.devlcc.core.network.HomeContentApiService
import io.github.devlcc.core.network.fake.FakeHomeContentApiService
import io.github.devlcc.core.network.impl.HomeContentApiServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.jetbrains.annotations.VisibleForTesting
import org.koin.core.module.Module
import org.koin.dsl.module

fun getCoreNetworkKoinModule(
    // This can be different based on buildType OR buildFlavor
    restApiEndpoint: String = "https://private-905cf-otaandroidtask.apiary-mock.com",
    isDebug: Boolean = true,
): Module = module {

    single<HttpClientEngine> {
        provideHttpClientEngine()
    }

    single<HttpClient> {
        provideKtorClient(
            engine = get(),
            json = get(),
            isDebug = isDebug,
        )
    }

    single<HomeContentApiService> {
        /**
         * TODO:: Replace with Concrete Implementation of Home Content API Service
         */
        FakeHomeContentApiService(
            json = get(),
        )
//        HomeContentApiServiceImpl(
//            restApiEndpoint = restApiEndpoint,
//            ktorClient = get(),
//        )
    }

}

private fun provideHttpClientEngine() =
    OkHttp.create {}

private fun provideKtorClient(
    engine: HttpClientEngine,
    json: Json,
    isDebug: Boolean,
): HttpClient =
    HttpClient(
        engine = engine,
    ) {
        expectSuccess = true
        developmentMode = isDebug

        defaultRequest {
            this.accept(ContentType.Application.Json)
            this.contentType(ContentType.Application.Json)
        }

        install(ContentNegotiation) {
            json(json)
        }

        install(HttpTimeout) {
            val timeout = 20_000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }
    }

@VisibleForTesting
fun testCoreNetworkKoinModule(
    json: Json = Json {
        prettyPrint = true
        isLenient = true
        ignoreUnknownKeys = true
    },
): List<Module> =
    getCoreNetworkKoinModule(
        restApiEndpoint = "http://127.0.0.1:8080",
        isDebug = true,
    ) + module {
        single<Json> { json }
    }