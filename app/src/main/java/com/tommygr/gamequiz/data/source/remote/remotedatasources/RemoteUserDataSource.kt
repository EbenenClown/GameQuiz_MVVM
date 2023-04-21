package com.tommygr.gamequiz.data.source.remote.remotedatasources

import com.tommygr.gamequiz.data.source.datamodels.UserDataModel
import com.tommygr.gamequiz.data.source.UserDataSource
import com.tommygr.gamequiz.data.source.remote.FirebaseAPI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RemoteUserDataSource(private val firebaseAPI: FirebaseAPI, private val dispatcher: CoroutineDispatcher = Dispatchers.IO): UserDataSource {
    override fun observeUser(id: String): Flow<UserDataModel> = flow { emit(firebaseAPI.getUserById(id)) }

    override suspend fun getUser(id: String): UserDataModel = withContext(dispatcher) {
        return@withContext firebaseAPI.getUserById(id)
    }

    override suspend fun refreshUser() {
        TODO("Not yet implemented")
    }

    override suspend fun saveNewUser(userDataModel: UserDataModel) = withContext(dispatcher) {
        return@withContext firebaseAPI.saveNewUser(userDataModel)
    }

    override suspend fun updateUser(userDataModel: UserDataModel) = withContext(dispatcher) {
        return@withContext firebaseAPI.updateUser(userDataModel.userId, userDataModel)
    }

}