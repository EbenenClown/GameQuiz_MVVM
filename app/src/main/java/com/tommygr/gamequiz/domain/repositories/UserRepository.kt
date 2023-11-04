package com.tommygr.gamequiz.domain.repositories


import com.tommygr.gamequiz.domain.domainmodels.UserDomainModel
import com.tommygr.gamequiz.util.Resource

interface UserRepository {

    suspend fun getUser(forceUpdate: Boolean = false): Resource<UserDomainModel>

    suspend fun refreshUser(id: String): Resource<UserDomainModel>

    suspend fun saveNewUser(userDomainModel: UserDomainModel)

    suspend fun registerUserWithEmailAndPassword(email: String, password: String): Resource<Unit>

    suspend fun loginUserWithEmailAndPassword(email: String, password: String): Resource<UserDomainModel>

    suspend fun updateUser(userDomainModel: UserDomainModel)

    suspend fun clear()
}