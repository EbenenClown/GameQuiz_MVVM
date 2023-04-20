package com.tommygr.gamequiz.data.source

import com.tommygr.gamequiz.data.User
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    fun observeUser(id: String): Flow<User>

    suspend fun getUser(id: String): User

    suspend fun refreshUser()

    suspend fun saveUser(user: User)

    suspend fun updateUser(user: User)
}