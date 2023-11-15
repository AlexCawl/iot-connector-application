plugins {
    id("android-setup")
}

dependencies {
    // Network
    implementation(ApplicationDeps.Ktor.ktorClient)
    implementation(ApplicationDeps.Ktor.ktorSerialization)
    implementation(ApplicationDeps.Ktor.ktorLogging)
}