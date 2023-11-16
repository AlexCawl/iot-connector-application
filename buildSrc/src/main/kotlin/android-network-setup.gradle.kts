plugins {
    id("android-setup")
}

android {
    packaging {
        resources {
            excludes += listOf("/META-INF/{AL2.0,LGPL2.1}", "META-INF/INDEX.LIST", "META-INF/io.netty.versions.properties")
        }
    }
}

dependencies {
    // Network
    implementation(ApplicationDeps.Ktor.ktorClient)
    implementation(ApplicationDeps.Ktor.ktorSerialization)
    implementation(ApplicationDeps.Ktor.ktorLogging)

    // Mqtt
    implementation(ApplicationDeps.Mqtt.mqttClient)
}