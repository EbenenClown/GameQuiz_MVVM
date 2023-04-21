package com.tommygr.gamequiz.domain

import com.tommygr.gamequiz.data.source.datamodels.UserDataModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun observeUser(id: String): Flow<UserDataModel>

    suspend fun getUser(id: String): UserDataModel

    suspend fun refreshUser()

    suspend fun saveNewUser(userDataModel: UserDataModel)

    suspend fun updateUser(userDataModel: UserDataModel)

}