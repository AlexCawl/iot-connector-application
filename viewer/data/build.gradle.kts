plugins {
    id("feature-module-setup")
}

android {
    namespace = "viewer.data".asModuleName()
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:network"))
    implementation(project(":core:persistence"))
}