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
import org.alexcawl.iot_connector.persistence.IoTConnectorDatabase
import org.alexcawl.iot_connector.persistence.dao.ConnectionDatabaseDao
import org.alexcawl.iot_connector.persistence.dao.ProfileDatabaseDao
import org.alexcawl.iot_connector.persistence.dao.ProfileDatastoreDao
import org.alexcawl.iot_connector.persistence.dao.impl.ProfileDatastoreDaoImpl

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
        fun provideConnectionDatabaseDao(database: IoTConnectorDatabase): ConnectionDatabaseDao = database.connectionDao()

        @Provides
        @ApplicationScope
        fun provideDatastore(context: Context): DataStore<Preferences> = context.datastore
        private val Context.datastore by preferencesDataStore("iot_connector_datastore")
    }

    @Binds
    fun bindProfileDatastoreDao(profileDatastoreDaoImpl: ProfileDatastoreDaoImpl): ProfileDatastoreDao
}