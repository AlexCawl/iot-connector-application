package org.alexcawl.iot_connector.di.module

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import org.alexcawl.iot_connector.di.ApplicationScope
import org.alexcawl.iot_connector.persistence.db.IoTConnectorDatabase
import org.alexcawl.iot_connector.persistence.db.dao.ProfileDao

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
        fun provideProfileDao(database: IoTConnectorDatabase): ProfileDao = database.profileDao()
    }
}