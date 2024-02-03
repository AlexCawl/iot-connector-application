plugins {
    id("feature-module-setup")
}

android {
    namespace = "connection.domain".asModuleName()
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":connection:data"))
}