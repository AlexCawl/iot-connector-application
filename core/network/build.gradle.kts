plugins {
    id("android-network-setup")
}

android {
    namespace = "network".asModuleName()
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:persistence"))
}