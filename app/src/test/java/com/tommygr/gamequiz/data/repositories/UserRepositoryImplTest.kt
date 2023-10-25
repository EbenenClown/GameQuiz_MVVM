package com.tommygr.gamequiz.data.repositories

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.tommygr.gamequiz.data.source.datamodels.UserDataModel
import com.tommygr.gamequiz.data.source.datamodels.mapper.toDomainModel
import com.tommygr.gamequiz.data.source.local.daos.UserDao
import com.tommygr.gamequiz.data.source.remote.remotedatasources.RemoteUserDataSource
import com.tommygr.gamequiz.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.io.IOException

class UserRepositoryImplTest {
    @RelaxedMockK
    private lateinit var mockLocalDataSource: UserDao
    @RelaxedMockK
    private lateinit var mockRemoteUserDataSource: RemoteUserDataSource
    private lateinit var userRepositoryImpl: UserRepositoryImpl

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        userRepositoryImpl = UserRepositoryImpl(mockLocalDataSource, mockRemoteUserDataSource)
    }

    @Test
    fun `get user, retrieve resource success with correct user`() = runBlocking {
        val user = UserDataModel("1", "iii@mail.com")
        coEvery { mockLocalDataSource.getUser() } returns user

        val receivedStatistic = userRepositoryImpl.getUser()

        assertThat(receivedStatistic).isInstanceOf(Resource.Success::class.java)
        assertThat(receivedStatistic.data).isEqualTo(user.toDomainModel())
    }

    @Test
    fun `get user throws exception, retrieve resource error with correct message`() = runBlocking {
        coEvery { mockLocalDataSource.getUser() } throws IOException()

        val receivedStatistic = userRepositoryImpl.getUser()

        assertThat(receivedStatistic).isInstanceOf(Resource.Error::class.java)
        assertThat(receivedStatistic.message).isEqualTo("java.io.IOException")
    }

    //TODO test firebase auth prob with integration test

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }
}