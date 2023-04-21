package com.tommygr.gamequiz.domain.repositories


import com.tommygr.gamequiz.domain.domainmodels.UserDomainModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun observeUser(id: String): Flow<UserDomainModel>

    suspend fun getUser(id: String): UserDomainModel

    suspend fun refreshUser(id: String)

    suspend fun saveNewUser(userDomainModel: UserDomainModel)

    suspend fun updateUser(userDomainModel: UserDomainModel)

}