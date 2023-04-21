package com.tommygr.gamequiz.data.source.local.daos


import androidx.room.*
import com.tommygr.gamequiz.data.source.datamodels.UserDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE userId=:userId")
    fun observeUser(userId: String): Flow<UserDataModel>

    @Query("SELECT * FROM user WHERE userId=:userId")
    fun getUser(userId: String): UserDataModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userDataModel: UserDataModel)

    @Update
    fun updateUser(userDataModel: UserDataModel)

    @Delete
    fun deleteUser(userDataModel: UserDataModel)

    @Query("DELETE FROM user")
    fun clear()
}