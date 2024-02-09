import gradle.kotlin.dsl.accessors._ba4f4cd60847fee9ce5f1b904b753fcd.implementation

plugins {
    id("com.android.application")

    // Kotlin Default
    id("org.jetbrains.kotlin.android")
    id("kotlinx-serialization")

    // Kotlin Ksp
    id("com.google.devtools.ksp")
}

android {
    compileSdk = ApplicationConfig.compileSdk
    namespace = ApplicationConfig.namespace
    buildToolsVersion = ApplicationConfig.buildToolsVersion

    defaultConfig {
        minSdk = ApplicationConfig.minSdk
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    compileOptions {
        sourceCompatibility = ApplicationConfig.javaVersion
        targetCompatibility = ApplicationConfig.javaVersion
    }

    kotlinOptions {
        jvmTarget = ApplicationConfig.jvmTarget
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = ApplicationConfig.kotlinComposeCompiler
    }

    packaging {
        resources {
            excludes += listOf("/META-INF/{AL2.0,LGPL2.1}", "META-INF/INDEX.LIST", "META-INF/io.netty.versions.properties")
        }
    }
}

dependencies {
    // Serialization
    implementation(ApplicationDeps.Serialization.core)
    implementation(ApplicationDeps.Serialization.json)

    // Coroutines
    implementation(ApplicationDeps.Kotlin.coroutines)
    implementation(ApplicationDeps.Android.coroutinesExtensions)

    // Dagger
    implementation(ApplicationDeps.Dagger.dagger)
    ksp(ApplicationDeps.Dagger.compiler)

    // Network
    implementation(ApplicationDeps.Ktor.ktorClient)
    implementation(ApplicationDeps.Ktor.ktorSerialization)
    implementation(ApplicationDeps.Ktor.ktorLogging)

    // Mqtt
    implementation(ApplicationDeps.Mqtt.mqttClient)

    // Room
    implementation(ApplicationDeps.Room.room)
    implementation(ApplicationDeps.Room.runtime)
    ksp(ApplicationDeps.Room.compiler)

    // Shared Preferences Datastore
    implementation(ApplicationDeps.Datastore.preferencesDatastore)

    // Android
    implementation(ApplicationDeps.Android.coreKtx)
    implementation(ApplicationDeps.Android.viewModel)
    implementation(ApplicationDeps.Android.runtime)
    implementation(ApplicationDeps.Android.lifecycleExtensions)

    // Compose
    implementation(ApplicationDeps.Compose.material3)
    implementation(ApplicationDeps.Compose.ui)
    implementation(ApplicationDeps.Compose.runtime)
    implementation(ApplicationDeps.Compose.activity)
    implementation(ApplicationDeps.Compose.viewModel)

    // Android Studio Preview support
    implementation(ApplicationDeps.Compose.uiToolingPreview)
    debugImplementation(ApplicationDeps.Compose.uiTooling)

    // Navigation
    implementation(ApplicationDeps.Navigation.compose)

    // Util
    implementation(ApplicationDeps.Utils.coil)
    implementation(ApplicationDeps.Utils.coilCompose)
    implementation(ApplicationDeps.Utils.splashScreen)

    // Testing
    testImplementation(ApplicationDeps.Test.junit)
    androidTestImplementation(ApplicationDeps.Test.androidJunit)
    androidTestImplementation(ApplicationDeps.Test.espresso)

    // Testing Compose
    androidTestImplementation(ApplicationDeps.Test.junitCompose)
    debugImplementation(ApplicationDeps.Test.manifestCompose)
    androidTestImplementation(ApplicationDeps.Test.navigationCompose)
}
