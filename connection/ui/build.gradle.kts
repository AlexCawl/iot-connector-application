plugins {
    id("feature-module-setup")
}

android {
    namespace = "connection.ui".asModuleName()
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":connection:domain"))
}