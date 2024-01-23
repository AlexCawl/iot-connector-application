plugins {
    id("application-module-setup")
}

android {
    buildTypes {
        debug {
            isMinifyEnabled = false
            applicationIdSuffix = ".debug"
            isDebuggable = true
        }

        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
}

dependencies {
    // core module
    implementation(project(":core:common"))
    implementation(project(":core:di"))
    implementation(project(":core:ui"))
    implementation(project(":core:network"))
    implementation(project(":core:persistence"))

    // connection module
    implementation(project(":connection"))
    implementation(project(":connection:dependencies"))

    // profile module
    implementation(project(":profile"))
    implementation(project(":profile:dependencies"))

    // viewer
    implementation(project(":viewer"))
    implementation(project(":viewer:dependencies"))
}