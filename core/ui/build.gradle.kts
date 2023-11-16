plugins {
    id("android-ui-setup")
}

android {
    namespace = "ui".asModuleName()
}

dependencies {
    implementation(project(":core:common"))
}