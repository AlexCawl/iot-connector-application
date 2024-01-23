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
include(":profile:dependencies")

// Viewer module
include(":viewer")
include(":viewer:dependencies")
