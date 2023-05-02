package com.tommygr.gamequiz.data.source.local.database


import androidx.room.*
import com.tommygr.gamequiz.data.source.datamodels.UserDataModel
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getUser(): UserDataModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNewUser(userDataModel: UserDataModel)

    @Update
    fun updateUser(userDataModel: UserDataModel)

    @Delete
    fun deleteUser(userDataModel: UserDataModel)

    @Query("DELETE FROM user")
    fun clear()
}