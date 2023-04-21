package com.tommygr.gamequiz.data.source.remote.remotedatasources

import com.tommygr.gamequiz.data.User
import com.tommygr.gamequiz.data.source.UserDataSource
import com.tommygr.gamequiz.data.source.remote.FirebaseAPI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RemoteUserDataSource(private val firebaseAPI: FirebaseAPI, private val dispatcher: CoroutineDispatcher = Dispatchers.IO): UserDataSource {
    override fun observeUser(id: String): Flow<User> = flow { emit(firebaseAPI.getUserById(id)) }

    override suspend fun getUser(id: String): User = withContext(dispatcher) {
        return@withContext firebaseAPI.getUserById(id)
    }

    override suspend fun refreshUser() {
        TODO("Not yet implemented")
    }

    override suspend fun saveUser(user: User) = withContext(dispatcher) {
        return@withContext firebaseAPI.saveNewUser(user)
    }

    override suspend fun updateUser(user: User) = withContext(dispatcher) {
        return@withContext firebaseAPI.updateUser(user.id, user)
    }

}