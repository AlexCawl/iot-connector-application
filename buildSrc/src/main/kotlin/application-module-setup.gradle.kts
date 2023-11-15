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

    buildTypes {
        debug {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }

        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = ApplicationConfig.javaVersion
        targetCompatibility = ApplicationConfig.javaVersion
    }

    kotlinOptions {
        jvmTarget = ApplicationConfig.jvmTarget
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

    // Room
    implementation(ApplicationDeps.Room.room)
    implementation(ApplicationDeps.Room.runtime)
    ksp(ApplicationDeps.Room.compiler)

    // Android
    implementation(ApplicationDeps.Android.coreKtx)
    implementation(ApplicationDeps.Android.appcompat)
    implementation(ApplicationDeps.Android.material)
    implementation(ApplicationDeps.Android.constraint)
    implementation(ApplicationDeps.Android.fragment)
    implementation(ApplicationDeps.Android.viewModel)
    implementation(ApplicationDeps.Android.runtime)
    implementation(ApplicationDeps.Android.lifecycleExtensions)

    // Navigation
    implementation(ApplicationDeps.Navigation.ui)
    implementation(ApplicationDeps.Navigation.fragment)

    // Util
    implementation(ApplicationDeps.Utils.coil)

    // Testing
    testImplementation(ApplicationDeps.Test.junit)
    androidTestImplementation(ApplicationDeps.Test.androidJunit)
    androidTestImplementation(ApplicationDeps.Test.espresso)
}
