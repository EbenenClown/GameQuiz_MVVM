package com.tommygr.gamequiz.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.tommygr.gamequiz.domain.repositories.DataStoreRepository
import com.tommygr.gamequiz.util.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreRepositoryImpl @Inject constructor(private val context: Context): DataStoreRepository {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Constants.DATASTORE_NAME)
    override suspend fun saveUserId(userId: String) {
        context.dataStore.edit {
            it[stringPreferencesKey(Constants.DATASTORE_USERID_KEY)] = userId
        }
    }

    override fun getUserId(): Flow<String> {
        return context.dataStore.data.map { it[stringPreferencesKey(Constants.DATASTORE_USERID_KEY)] ?: "" }
    }
}