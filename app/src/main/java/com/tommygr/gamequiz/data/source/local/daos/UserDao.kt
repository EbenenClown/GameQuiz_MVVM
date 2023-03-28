package com.tommygr.gamequiz.data.source.local.daos


import androidx.room.*
import com.tommygr.gamequiz.data.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM User WHERE userId=:userId")
    fun observeUser(userID: String): Flow<User>

    @Query("SELECT * FROM User WHERE userId=:userId")
    fun getUser(userId: String): User

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun deleteUser(user: User)

    @Query("DELETE FROM user")
    fun clear()
}