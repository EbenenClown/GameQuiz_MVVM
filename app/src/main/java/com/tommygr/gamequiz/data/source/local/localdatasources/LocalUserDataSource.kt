package com.tommygr.gamequiz.data.source.local.localdatasources

import com.tommygr.gamequiz.data.Result
import com.tommygr.gamequiz.data.User
import com.tommygr.gamequiz.data.source.UserDataSource
import com.tommygr.gamequiz.data.source.local.daos.UserDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LocalUserDataSource internal constructor(private val userDao: UserDao, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): UserDataSource {
    override fun observeUser(id: String): Flow<Result<User>> {
        return userDao.observeUser(id).map {
            Result.Success(it)
        }
    }

    override suspend fun getUser(id: String) = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(userDao.getUser(id))
        } catch (e: java.lang.Exception) {
            Result.Error(e)
        }
    }

    override suspend fun refreshUser() {
        TODO("Not yet implemented")
    }

    override suspend fun saveUser(user: User) = withContext(ioDispatcher) {
        userDao.insertUser(user)
    }

    override suspend fun updateUser(user: User) = withContext(ioDispatcher) {
        userDao.updateUser(user)
    }
}