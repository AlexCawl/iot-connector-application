plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenLocal()
    gradlePluginPortal()
}

dependencies {
    implementation(ApplicationDeps.Kotlin.gradlePlugin)
    implementation(ApplicationDeps.Serialization.gradlePlugin)
    implementation(ApplicationDeps.Ksp.gradlePlugin)
    implementation(ApplicationDeps.Android.applicationPlugin)
    implementation(ApplicationDeps.Android.libraryPlugin)
}

kotlin {
    sourceSets.getByName("main").kotlin.srcDir("buildSrc/src/main/kotlin")
}