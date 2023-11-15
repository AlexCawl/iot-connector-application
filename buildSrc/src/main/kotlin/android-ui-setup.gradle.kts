plugins {
    id("android-setup")
}

dependencies {
    // Android
    implementation(ApplicationDeps.Android.appcompat)
    implementation(ApplicationDeps.Android.material)
    implementation(ApplicationDeps.Android.constraint)

    // Navigation
    implementation(ApplicationDeps.Navigation.ui)
    implementation(ApplicationDeps.Navigation.fragment)

    // Util
    implementation(ApplicationDeps.Utils.coil)

    // Testing
    androidTestImplementation(ApplicationDeps.Test.espresso)
}

android {
    buildFeatures {
        viewBinding = true
        compose = false
    }
}