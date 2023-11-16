plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
    id("kotlinx-serialization")
    id("com.google.devtools.ksp")
}

java {
    sourceCompatibility = ApplicationConfig.javaVersion
    targetCompatibility = ApplicationConfig.javaVersion
}

dependencies {
    // Serialization
    implementation(ApplicationDeps.Serialization.core)
    implementation(ApplicationDeps.Serialization.json)

    // Coroutines
    implementation(ApplicationDeps.Kotlin.coroutines)

    // Dagger
    implementation(ApplicationDeps.Dagger.dagger)
    ksp(ApplicationDeps.Dagger.compiler)

    // Testing
    testImplementation(ApplicationDeps.Test.junit)
}