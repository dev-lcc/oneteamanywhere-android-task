plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.sqlDelight)
}

android {
    namespace = "io.github.devlcc.core.database"
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

    // Local Database
    implementation(libs.sqlDelight.runtime)
    implementation(libs.sqlDelight.driver.android)
    implementation(libs.sqlDelight.driver.sqlite)
    implementation(libs.sqlDelight.coroutines.extensions)
    // https://github.com/xerial/sqlite-jdbc?tab=readme-ov-file
    testImplementation("org.xerial:sqlite-jdbc") {
        // Override the version of sqlite used by sqlite-driver to match Android API 24
        version { strictly("3.45.2.0") }
    }

    implementation(libs.koin.core)
    testImplementation(libs.koin.test)

    implementation(libs.kotlinx.serialization.json)
}

sqldelight {
    databases {
        create("OTAAndroidTaskDatabase") {
            packageName.set("io.github.devlcc.core.database")
            dialect(libs.sqlDelight.dialect.sqlite338)
        }
    }
}