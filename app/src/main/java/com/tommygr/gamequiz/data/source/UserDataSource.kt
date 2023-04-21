package com.tommygr.gamequiz.data.source

import com.tommygr.gamequiz.data.source.datamodels.UserDataModel
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    fun observeUser(id: String): Flow<UserDataModel>

    suspend fun getUser(id: String): UserDataModel

    suspend fun refreshUser()

    suspend fun saveNewUser(userDataModel: UserDataModel)

    suspend fun updateUser(userDataModel: UserDataModel)
}