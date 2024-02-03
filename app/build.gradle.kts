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
    implementation(project(":profile:data"))
    implementation(project(":profile:domain"))
    implementation(project(":profile:ui"))

    // viewer module
    implementation(project(":viewer"))
    implementation(project(":viewer:dependencies"))

    // title module
    implementation(project(":title"))
    implementation(project(":title:dependencies"))
}