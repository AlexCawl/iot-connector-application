plugins {
    id("feature-module-setup")
}

android {
    namespace = "client.ui".asModuleName()
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":client:domain"))
}