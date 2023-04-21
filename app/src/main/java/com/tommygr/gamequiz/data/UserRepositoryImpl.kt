package com.tommygr.gamequiz.data

import com.tommygr.gamequiz.data.source.datamodels.UserDataModel
import com.tommygr.gamequiz.data.source.local.localdatasources.LocalUserDataSource
import com.tommygr.gamequiz.data.source.remote.remotedatasources.RemoteUserDataSource
import com.tommygr.gamequiz.domain.UserRepository
import kotlinx.coroutines.flow.Flow

class UserRepositoryImpl(private val localUserDataSource: LocalUserDataSource, private val remoteUserDataSource: RemoteUserDataSource):
    UserRepository {
    override fun observeUser(id: String): Flow<UserDataModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(id: String): UserDataModel {
        TODO("Not yet implemented")
    }

    override suspend fun refreshUser() {
        TODO("Not yet implemented")
    }

    override suspend fun saveNewUser(userDataModel: UserDataModel) {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(userDataModel: UserDataModel) {
        TODO("Not yet implemented")
    }
}