package org.alexcawl.iot_connector.persistence.pref.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.alexcawl.iot_connector.persistence.pref.dao.ProfileDatastoreDao
import java.util.UUID
import javax.inject.Inject

class ProfileDatastoreSource @Inject constructor(
    private val datastore: DataStore<Preferences>
) : ProfileDatastoreDao {
    private companion object {
        val selectedProfileKey: Preferences.Key<String> = stringPreferencesKey("selected_profile")
    }

    override fun getSelectedProfileId(): Flow<UUID?> = datastore.data.map { prefs: Preferences ->
        prefs[selectedProfileKey]?.let { id: String ->
            try {
                UUID.fromString(id)
            } catch (exception: IllegalArgumentException) {
                null
            }
        }
    }

    override suspend fun setSelectedProfileId(id: UUID?) {
        datastore.edit { prefs: MutablePreferences ->
            prefs[selectedProfileKey] = id.toString()
        }
    }
}