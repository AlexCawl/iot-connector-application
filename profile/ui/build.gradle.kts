plugins {
    id("feature-module-setup")
}

android {
    namespace = "profile.ui".asModuleName()
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":profile:domain"))
}