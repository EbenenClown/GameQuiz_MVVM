package com.tommygr.gamequiz.domain.repositories

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface DataStoreRepository {
    suspend fun saveUserId(userId: String)
    fun getUserId(): Flow<String>
}