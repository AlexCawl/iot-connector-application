plugins {
    id("android-setup")
    id("com.google.devtools.ksp")
}

dependencies {
    // Room
    implementation(ApplicationDeps.Room.room)
    implementation(ApplicationDeps.Room.runtime)
    ksp(ApplicationDeps.Room.compiler)

    // Shared Preferences Datastore
    implementation(ApplicationDeps.Datastore.preferencesDatastore)
}