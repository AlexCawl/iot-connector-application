plugins {
    id("feature-module-setup")
}

android {
    namespace = "client.domain".asModuleName()
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:ui"))
    implementation(project(":client:data"))
}