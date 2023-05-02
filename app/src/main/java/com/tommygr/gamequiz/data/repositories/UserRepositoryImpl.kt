package com.tommygr.gamequiz.data.repositories

import com.tommygr.gamequiz.data.source.datamodels.mapper.toDataModel
import com.tommygr.gamequiz.data.source.datamodels.mapper.toDomainModel
import com.tommygr.gamequiz.data.source.local.database.UserDao
import com.tommygr.gamequiz.data.source.remote.remotedatasources.RemoteUserDataSource
import com.tommygr.gamequiz.domain.repositories.UserRepository
import com.tommygr.gamequiz.domain.domainmodels.UserDomainModel

class UserRepositoryImpl(private val localUserDataSource: UserDao, private val remoteUserDataSource: RemoteUserDataSource): UserRepository {


    override suspend fun getUser(forceUpdate: Boolean): UserDomainModel = localUserDataSource.getUser().toDomainModel()

    override suspend fun refreshUser(id: String) {
        val remoteUser = remoteUserDataSource.getUser(id)
        localUserDataSource.addNewUser(remoteUser)
    }

    override suspend fun saveNewUser(userDomainModel: UserDomainModel) {
        TODO("Not yet implemented")
    }

    override suspend fun updateUser(userDomainModel: UserDomainModel) {
        localUserDataSource.updateUser(userDomainModel.toDataModel())
        remoteUserDataSource.updateUser(userDomainModel.toDataModel())
    }

}