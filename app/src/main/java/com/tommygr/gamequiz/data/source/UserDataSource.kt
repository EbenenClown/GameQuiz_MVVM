package com.tommygr.gamequiz.data.source

import com.tommygr.gamequiz.data.User
import com.tommygr.gamequiz.data.Result
import kotlinx.coroutines.flow.Flow

interface UserDataSource {
    fun observeUser(id: String): Flow<Result<User>>

    fun getUser(id: String): Result<User>

    fun refreshUser(id: String)

    fun saveUser(id: String)

    fun updateUser(id: String)
}