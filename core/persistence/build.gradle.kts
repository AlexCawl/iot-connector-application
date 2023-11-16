plugins {
    id("android-storage-setup")
}

android {
    namespace = "persistence".asModuleName()
}

dependencies {
    implementation(project(":core:common"))
}