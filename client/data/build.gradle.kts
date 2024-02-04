plugins {
    id("feature-module-setup")
}

android {
    namespace = "client.data".asModuleName()
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:network"))
    implementation(project(":core:persistence"))
}