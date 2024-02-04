plugins {
    id("feature-module-setup")
}

android {
    namespace = "client".asModuleName()
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:di"))
    implementation(project(":core:network"))
    implementation(project(":core:ui"))
    implementation(project(":core:persistence"))

    implementation(project(":client:data"))
    implementation(project(":client:domain"))
    implementation(project(":client:ui"))
}