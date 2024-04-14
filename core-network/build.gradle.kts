plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "io.github.devlcc.core.network"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
        freeCompilerArgs = listOf(
            "-Xstring-concat=inline",
            "-opt-in=kotlinx.coroutines.ExperimentalCoroutinesApi",
        )
    }
}

dependencies {
    implementation(project(":core-model"))

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.kotlinx.serialization.json)
    testImplementation(libs.kotlinx.coroutines.test)

    implementation(libs.ktor.client.core)
    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.engine.okhttp)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.contentNegotiation)
    testImplementation(libs.ktor.client.mock)

    implementation(libs.koin.core)
    testImplementation(libs.koin.test)

    implementation(libs.kotlinx.serialization.json)

}