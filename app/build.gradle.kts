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

    // connection module
    implementation(project(":connection:di"))
    implementation(project(":connection:data"))
    implementation(project(":connection:domain"))
    implementation(project(":connection:ui"))
}