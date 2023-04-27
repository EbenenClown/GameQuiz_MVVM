package com.tommygr.gamequiz.data.source.local.localdatasources

import com.tommygr.gamequiz.data.source.datamodels.UserDataModel
import com.tommygr.gamequiz.data.source.UserDataSource
import com.tommygr.gamequiz.data.source.local.database.UserDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalUserDataSource internal constructor(private val userDao: UserDao, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): UserDataSource {
    override fun observeUser(id: String) = userDao.observeUser(id)

    override suspend fun getUser(id: String) = withContext(ioDispatcher) {
        return@withContext userDao.getUser(id)
    }

    override suspend fun refreshUser() {
        TODO("Not yet implemented")
    }

    override suspend fun saveNewUser(userDataModel: UserDataModel) = withContext(ioDispatcher) {
        userDao.insertUser(userDataModel)
    }

    override suspend fun updateUser(userDataModel: UserDataModel) = withContext(ioDispatcher) {
        userDao.updateUser(userDataModel)
    }
}