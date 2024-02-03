pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "IoTConnector"
include(":app")

// Core modules
include(":core:common")
include(":core:di")
include(":core:ui")
include(":core:network")
include(":core:persistence")

// Connection module
include(":connection")
include(":connection:dependencies")

// Profile module
include(":profile")
include(":profile:data")
include(":profile:domain")
include(":profile:ui")

// Viewer module
include(":viewer")
include(":viewer:dependencies")

// Title module
include(":title")
include(":title:dependencies")
include(":profile:data")
include(":profile:domain")
include(":profile:ui")
