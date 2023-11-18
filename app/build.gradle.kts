plugins {
    id("application-module-setup")
}

dependencies {
    // core module
    implementation(project(":core:common"))
    implementation(project(":core:di"))
    implementation(project(":core:ui"))
    implementation(project(":core:network"))
    implementation(project(":core:persistence"))

    // connections module
    implementation(project(":connections"))
    implementation(project(":connections:dependencies"))

    // profile module
    implementation(project(":profile"))
    implementation(project(":profile:dependencies"))
}