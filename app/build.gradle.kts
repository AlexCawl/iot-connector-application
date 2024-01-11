plugins {
    id("application-module-setup")
}

dependencies {
    // core module
    implementation(project(":core:common"))
    implementation(project(":core:di"))
    implementation(project(":core:ui"))
    implementation(project(":core:persistence"))

    // connection module
    implementation(project(":connection"))
    implementation(project(":connection:dependencies"))

    // profile module
    implementation(project(":profile"))
    implementation(project(":profile:dependencies"))

    // network module
    implementation(project(":network"))
    implementation(project(":network:dependencies"))
}