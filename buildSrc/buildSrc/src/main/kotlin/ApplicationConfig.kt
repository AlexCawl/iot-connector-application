import org.gradle.api.JavaVersion

object ApplicationConfig {
    val namespace = "org.alexcawl.iot_connector"
    val compileSdk = 34
    val applicationId = namespace
    val minSdk = 24
    val targetSdk = 34
    val versionCode = 1
    val versionName = "1.0"
    val buildToolsVersion  = "34.0.0"
    val javaVersion = JavaVersion.VERSION_17
    val jvmTarget = "17"
    val kotlinComposeCompiler = "1.5.2"
}

