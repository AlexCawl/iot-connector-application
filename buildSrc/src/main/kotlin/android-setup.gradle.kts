plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlinx-serialization")
    id("com.google.devtools.ksp")
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

    // Testing
    testImplementation(ApplicationDeps.Test.junit)
    androidTestImplementation(ApplicationDeps.Test.androidJunit)

    // Android
    implementation(ApplicationDeps.Android.coreKtx)
    implementation(ApplicationDeps.Android.viewModel)
    implementation(ApplicationDeps.Android.runtime)
    implementation(ApplicationDeps.Android.lifecycleExtensions)
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
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
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