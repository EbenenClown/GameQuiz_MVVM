package com.tommygr.gamequiz.data.repositories

import com.tommygr.gamequiz.data.source.datamodels.mapper.toDataModel
import com.tommygr.gamequiz.data.source.datamodels.mapper.toDomainModel
import com.tommygr.gamequiz.data.source.local.daos.UserDao
import com.tommygr.gamequiz.data.source.remote.remotedatasources.RemoteUserDataSource
import com.tommygr.gamequiz.domain.repositories.UserRepository
import com.tommygr.gamequiz.domain.domainmodels.UserDomainModel
import com.tommygr.gamequiz.util.Resource
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import kotlin.Exception

class UserRepositoryImpl @Inject constructor(private val localUserDataSource: UserDao, private val remoteUserDataSource: RemoteUserDataSource): UserRepository {

    override suspend fun getUser(userId: String, forceUpdate: Boolean): Resource<UserDomainModel> {
        return try {
            Resource.Success(localUserDataSource.getUser(userId).toDomainModel())
        } catch (e: Exception) {
            Resource.Error(e.toString())
        }
    }

    override suspend fun refreshUser(id: String): Resource<UserDomainModel> {
        return try {
            val remoteFirebaseUser = remoteUserDataSource.getUser()
            remoteFirebaseUser?.let {
                localUserDataSource.addUser(it.toDataModel())
                Resource.Success(it.toDomainModel())
            }
            Resource.Error("Could not refresh user because no user is logged in")
        } catch (e: Exception) {

            Resource.Error(e.toString())
        }
    }

    override suspend fun saveNewUser(userDomainModel: UserDomainModel) {
        localUserDataSource.addUser(userDomainModel.toDataModel())
    }

    override suspend fun registerUserWithEmailAndPassword(email: String, password: String): Resource<Unit> {
        return try {
            remoteUserDataSource.registerUserWithEmailAndPassword(email, password).await()
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun loginUserWithEmailAndPassword(email: String, password: String): Resource<UserDomainModel> {
        return try {
            val result = remoteUserDataSource.loginUserWithEmailAndPassword(email, password).await()
            Resource.Success(result.user?.toDomainModel())
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }

    override suspend fun updateUser(userDomainModel: UserDomainModel) {
        localUserDataSource.updateUser(userDomainModel.toDataModel())
    }

}