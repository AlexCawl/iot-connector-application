package org.alexcawl.iot_connector.di.module

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import dagger.Binds
import dagger.Module
import dagger.Provides
import org.alexcawl.iot_connector.di.ApplicationScope
import org.alexcawl.iot_connector.persistence.db.IoTConnectorDatabase
import org.alexcawl.iot_connector.persistence.db.dao.ConnectionDatabaseDao
import org.alexcawl.iot_connector.persistence.db.dao.ProfileDatabaseDao
import org.alexcawl.iot_connector.persistence.pref.dao.ProfileDatastoreDao
import org.alexcawl.iot_connector.persistence.pref.source.ProfileDatastoreSource

@Module
interface PersistenceModule {
    companion object {
        @Provides
        @ApplicationScope
        fun provideDatabase(context: Context): IoTConnectorDatabase {
            return Room.databaseBuilder(
                context = context,
                klass = IoTConnectorDatabase::class.java,
                name = "${IoTConnectorDatabase::class.java}"
            ).build()
        }

        @Provides
        @ApplicationScope
        fun provideProfileDatabaseDao(database: IoTConnectorDatabase): ProfileDatabaseDao = database.profileDao()

        @Provides
        @ApplicationScope
        fun provideConnectionDatastoreDao(database: IoTConnectorDatabase): ConnectionDatabaseDao = database.connectionDao()

        @Provides
        @ApplicationScope
        fun provideDatastore(context: Context): DataStore<Preferences> = context.datastore
        private val Context.datastore by preferencesDataStore("iot_connector_datastore")
    }

    @Binds
    fun bindProfileDatastoreDao(profileDatastoreSource: ProfileDatastoreSource): ProfileDatastoreDao
}