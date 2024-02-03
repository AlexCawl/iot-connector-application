plugins {
    id("feature-module-setup")
}

android {
    namespace = "viewer.ui".asModuleName()
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:di"))
    implementation(project(":core:ui"))
    implementation(project(":viewer:domain"))
}