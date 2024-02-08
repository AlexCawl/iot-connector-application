plugins {
    id("android-setup")
}

dependencies {
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
    androidTestImplementation(ApplicationDeps.Test.espresso)
    androidTestImplementation(ApplicationDeps.Test.junitCompose)
    debugImplementation(ApplicationDeps.Test.manifestCompose)
    androidTestImplementation(ApplicationDeps.Test.navigationCompose)
}

android {
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = ApplicationConfig.kotlinComposeCompiler
    }
}