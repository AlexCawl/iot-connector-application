plugins {
    id("android-setup")
}

android {
    namespace = "di".asModuleName()
}

dependencies {
    implementation(project(":core:common"))
}