plugins {
    id("feature-module-setup")
}

android {
    namespace = "connection".asModuleName()
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:di"))
    implementation(project(":core:network"))
    implementation(project(":core:ui"))
    implementation(project(":core:persistence"))

    implementation(project(":connection:data"))
    implementation(project(":connection:domain"))
    implementation(project(":connection:ui"))
}