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

include(":core:common")
include(":core:di")
include(":core:ui")
include(":core:network")
include(":core:persistence")

include(":connection:di")
include(":connection:data")
include(":connection:domain")
include(":connection:ui")
