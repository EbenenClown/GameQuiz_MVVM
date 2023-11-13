package com.tommygr.gamequiz.data.local.daos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.tommygr.gamequiz.data.source.datamodels.UserDataModel
import com.tommygr.gamequiz.data.source.local.GameQuizDb
import com.tommygr.gamequiz.data.source.local.daos.UserDao
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UserDaoTest {

    private lateinit var userDao: UserDao
    private lateinit var database: GameQuizDb

    @Before
    fun setUp() {
       database = Room.inMemoryDatabaseBuilder(
           ApplicationProvider.getApplicationContext(),
           GameQuizDb::class.java
       ).allowMainThreadQueries().build()

        userDao = database.userDao()
    }

    @Test
    fun insertUser_RetrieveCorrectUser() {
        val user = UserDataModel("1", "iii@mail.com")
        userDao.addUser(user)

        val retrievedUser = userDao.getUser()
        assertThat(retrievedUser).isEqualTo(user)
    }

    @Test
    fun updateUser_correctChanges() {
        val user = UserDataModel("1", "iii@mail.com")
        userDao.addUser(user)

        val newUserEmail = UserDataModel("1", "aaa@mail.com")
        userDao.updateUser(newUserEmail)

        val retrievedUser = userDao.getUser()
        assertThat(retrievedUser).isEqualTo(newUserEmail)
    }

    @Test
    fun deleteUser_UserIsNull() {
        val user = UserDataModel("1", "iii@mail.com")
        userDao.addUser(user)

        userDao.deleteUser(user)

        val retrievedUser = userDao.getUser()
        assertThat(retrievedUser).isNull()
    }

    @Test
    fun clearTable_UserIsNull() {
        val user = UserDataModel("1", "iii@mail.com")
        userDao.addUser(user)

        userDao.clear()

        val retrievedUser = userDao.getUser()
        assertThat(retrievedUser).isNull()
    }

    @After
    fun tearDown() {
        database.close()
    }
}