package com.tommygr.gamequiz.domain.usecases

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.tommygr.gamequiz.domain.domainmodels.UserDomainModel
import com.tommygr.gamequiz.domain.repositories.UserRepository
import io.mockk.MockKAnnotations
import io.mockk.clearMocks
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CreateLocalUserUseCaseTest {
    @RelaxedMockK
    private lateinit var userRepository: UserRepository
    private lateinit var createLocalUserUseCase: CreateLocalUserUseCase
    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        createLocalUserUseCase = CreateLocalUserUseCase(userRepository)
    }

    @Test
    fun `invoke create new User, check for saving correctly`() = runBlocking {
        createLocalUserUseCase()

        coVerify { userRepository.saveNewUser(match { !it.userId.isNullOrEmpty() && it.email.isEmpty() }) }
    }

    @Test
    fun `test unique user IDs`() = runBlocking {
        val useCase = CreateLocalUserUseCase(userRepository)
        val generatedIds = mutableSetOf<String>()

        repeat(100) {
            useCase()
            coVerify { userRepository.saveNewUser(match { user -> generatedIds.add(user.userId) }) }
        }

        assertThat(generatedIds.size).isEqualTo(100)
    }

    @AfterEach
    fun tearDown() {
        clearMocks(userRepository)
    }
}