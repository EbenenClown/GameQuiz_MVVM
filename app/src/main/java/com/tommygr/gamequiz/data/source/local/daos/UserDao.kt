package com.tommygr.gamequiz.data.source.local.daos


import androidx.room.*
import com.tommygr.gamequiz.data.source.datamodels.UserDataModel

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUser(): UserDataModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addUser(userDataModel: UserDataModel)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(userDataModel: UserDataModel)

    @Delete
    fun deleteUser(userDataModel: UserDataModel)

    @Query("DELETE FROM user")
    fun clear()
}