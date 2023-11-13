package com.tommygr.gamequiz.domain.usecases

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.tommygr.gamequiz.domain.domainmodels.UserDomainModel
import com.tommygr.gamequiz.domain.repositories.DataStoreRepository
import com.tommygr.gamequiz.domain.repositories.UserRepository
import io.mockk.MockKAnnotations
import io.mockk.clearMocks
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

//TODO: Datastore testing
class CreateLocalUserUseCaseTest {
    @RelaxedMockK
    private lateinit var mockUserRepository: UserRepository

    @RelaxedMockK
    private lateinit var mockDataStoreRepository: DataStoreRepository
    private lateinit var createLocalUserUseCase: CreateLocalUserUseCase

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        createLocalUserUseCase = CreateLocalUserUseCase(mockUserRepository, mockDataStoreRepository)
    }

    @Test
    fun `create new user with default parameter, check for saving correctly`() = runBlocking {
        createLocalUserUseCase()

        coVerify { mockUserRepository.saveNewUser(match { it.userId.isNotEmpty() && it.userName.isEmpty() && it.email.isEmpty() && !it.isRegistered }) }
    }

    @Test
    fun `create new user with given user, check for saving correctly`() = runBlocking {
        val user = UserDomainModel("1", "Name", "email@email.com", true)
        createLocalUserUseCase(user)

        coVerify { mockUserRepository.saveNewUser(match { it == user }) }
    }

    @Test
    fun `test unique user IDs`() = runBlocking {
        val useCase = CreateLocalUserUseCase(mockUserRepository, mockDataStoreRepository)
        val generatedIds = mutableSetOf<String>()

        repeat(100) {
            useCase()
            coVerify { mockUserRepository.saveNewUser(match { user -> generatedIds.add(user.userId) }) }
        }

        assertThat(generatedIds.size).isEqualTo(100)
    }

    @AfterEach
    fun tearDown() {
        clearMocks(mockUserRepository)
    }
}