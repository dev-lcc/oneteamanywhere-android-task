package io.github.devlcc.core.network.di

import kotlinx.serialization.json.Json
import org.jetbrains.annotations.VisibleForTesting
import org.koin.core.module.Module
import org.koin.dsl.module

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