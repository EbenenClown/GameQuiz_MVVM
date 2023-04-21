package com.tommygr.gamequiz.data.repositories

import com.tommygr.gamequiz.data.source.datamodels.mapper.toDataModel
import com.tommygr.gamequiz.data.source.datamodels.mapper.toDomainModel
import com.tommygr.gamequiz.data.source.local.localdatasources.LocalUserDataSource
import com.tommygr.gamequiz.data.source.remote.remotedatasources.RemoteUserDataSource
import com.tommygr.gamequiz.domain.repositories.UserRepository
import com.tommygr.gamequiz.domain.domainmodels.UserDomainModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(private val localUserDataSource: LocalUserDataSource, private val remoteUserDataSource: RemoteUserDataSource):
    UserRepository {
    override fun observeUser(id: String): Flow<UserDomainModel> = localUserDataSource.observeUser(id).map { it.toDomainModel() }

    override suspend fun getUser(id: String): UserDomainModel = localUserDataSource.getUser(id).toDomainModel()

    override suspend fun refreshUser(id: String) {
        val remoteUser = remoteUserDataSource.getUser(id)
        localUserDataSource.saveNewUser(remoteUser)
    }

    override suspend fun saveNewUser(userDomainModel: UserDomainModel) {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(userDomainModel: UserDomainModel) {
        localUserDataSource.updateUser(userDomainModel.toDataModel())
        remoteUserDataSource.updateUser(userDomainModel.toDataModel())
    }

}