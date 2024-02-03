plugins {
    id("feature-module-setup")
}

android {
    namespace = "viewer.domain".asModuleName()
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":viewer:data"))
}