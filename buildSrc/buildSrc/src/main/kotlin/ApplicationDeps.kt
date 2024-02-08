object ApplicationDeps {
    private object Version {
        val kotlin: String = "1.9.0"
        val serialization: String = "1.5.1"
        val coroutines: String = "1.7.3"
        val ksp: String = "1.9.0-1.0.12"
        val agp: String = "8.1.2"
        val coreKtx: String = "1.10.1"
        val lifecycle: String = "2.6.1"
        val lifecycleExt: String = "2.2.0"
        val ktor: String = "1.5.0"
        val room: String = "2.5.2"
        val datastore = "1.0.0"
        val navigation: String = "2.7.5"
        val dagger: String = "2.48"
        val compose: String = "1.5.4"
        val composeCompiler: String = ApplicationConfig.kotlinComposeCompiler
    }

    object Kotlin {
        val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"

        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
    }

    object Serialization {
        val gradlePlugin = "org.jetbrains.kotlin:kotlin-serialization:${Version.kotlin}"

        val core = "org.jetbrains.kotlinx:kotlinx-serialization-core:${Version.serialization}"
        val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:${Version.serialization}"
    }

    object Ksp {
        val gradlePlugin = "com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:${Version.ksp}"
    }

    object Android {
        val applicationPlugin = "com.android.application:com.android.application.gradle.plugin:${Version.agp}"
        val libraryPlugin = "com.android.library:com.android.library.gradle.plugin:${Version.agp}"

        val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
        val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
        val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
        val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Version.lifecycleExt}"
        val coroutinesExtensions = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
    }

    object Compose {
        val material3 = "androidx.compose.material3:material3:1.1.2"
        val ui = "androidx.compose.ui:ui:${Version.compose}"
        val uiTooling = "androidx.compose.ui:ui-tooling:${Version.compose}"
        val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Version.compose}"
        val runtime = "androidx.compose.runtime:runtime:${Version.compose}"

        val activity = "androidx.activity:activity-compose:1.7.2"
        val viewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1"
    }

    object Test {
        val junit = "junit:junit:4.13.2"
        val androidJunit = "androidx.test.ext:junit:1.1.5"
        val espresso = "androidx.test.espresso:espresso-core:3.5.1"

        val junitCompose = "androidx.compose.ui:ui-test-junit4:${Version.compose}"
        val manifestCompose = "androidx.compose.ui:ui-test-manifest:${Version.compose}"
        val navigationCompose = "androidx.navigation:navigation-testing:${Version.navigation}"
    }

    object Ktor {
        val ktorClient = "io.ktor:ktor-client-android:${Version.ktor}"
        val ktorSerialization = "io.ktor:ktor-client-serialization:${Version.ktor}"
        val ktorLogging = "io.ktor:ktor-client-logging-jvm:${Version.ktor}"
    }

    object Mqtt {
        val mqttClient = "com.hivemq:hivemq-mqtt-client:1.3.0"
    }

    object Room {
        val runtime = "androidx.room:room-runtime:${Version.room}"
        val compiler = "androidx.room:room-compiler:${Version.room}"
        val room = "androidx.room:room-ktx:${Version.room}"
    }

    object Datastore {
        val preferencesDatastore = "androidx.datastore:datastore-preferences:${Version.datastore}"
    }

    object Navigation {
        val compose = "androidx.navigation:navigation-compose:${Version.navigation}"
    }

    object Dagger {
        val dagger = "com.google.dagger:dagger:${Version.dagger}"
        val compiler = "com.google.dagger:dagger-compiler:${Version.dagger}"
    }

    object Utils {
        val coil = "io.coil-kt:coil:2.4.0"
        val coilCompose = "io.coil-kt:coil-compose:2.4.0"
        val splashScreen = "androidx.core:core-splashscreen:1.0.1"
    }
}