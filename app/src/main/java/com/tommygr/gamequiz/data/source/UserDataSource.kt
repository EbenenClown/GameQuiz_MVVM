package com.tommygr.gamequiz.data.source

import com.tommygr.gamequiz.data.User
import com.tommygr.gamequiz.data.Result
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    fun observeUser(id: String): Flow<Result<User>>

    suspend fun getUser(id: String): Result<User>

    suspend fun refreshUser()

    suspend fun saveUser(user: User)

    suspend fun updateUser(user: User)
}