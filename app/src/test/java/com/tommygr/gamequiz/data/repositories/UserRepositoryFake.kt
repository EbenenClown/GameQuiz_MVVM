package com.tommygr.gamequiz.data.repositories

import com.tommygr.gamequiz.domain.domainmodels.UserDomainModel
import com.tommygr.gamequiz.domain.repositories.UserRepository
import com.tommygr.gamequiz.util.Resource

class UserRepositoryFake: UserRepository {
    private val userList = mutableListOf<UserDomainModel>()
    private var isError = false
    override suspend fun getUser(userId: String, forceUpdate: Boolean): Resource<UserDomainModel> {
        userList.firstOrNull()?.let {
            return Resource.Success(it)
        }
        isError = true
        return Resource.Error("No User Found")
    }

    override suspend fun refreshUser(id: String): Resource<UserDomainModel> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNewUser(userDomainModel: UserDomainModel) {
        userList.add(userDomainModel)
    }

    override suspend fun registerUserWithEmailAndPassword(
        email: String,
        password: String
    ): Resource<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun loginUserWithEmailAndPassword(
        email: String,
        password: String
    ): Resource<UserDomainModel> {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(userDomainModel: UserDomainModel) {
        TODO("Not yet implemented")
    }

}