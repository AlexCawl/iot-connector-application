plugins {
    id("feature-module-setup")
}

android {
    namespace = "profile.domain".asModuleName()
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":profile:data"))
}