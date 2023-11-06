package com.tommygr.gamequiz.domain.usecases

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.tommygr.gamequiz.domain.domainmodels.UserDomainModel
import com.tommygr.gamequiz.domain.repositories.UserRepository
import com.tommygr.gamequiz.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RefreshUserUseCaseTest {
    @RelaxedMockK
    private lateinit var mockUserRepository: UserRepository
    private lateinit var refreshUserUseCase: RefreshUserUseCase

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        refreshUserUseCase = RefreshUserUseCase(mockUserRepository)
    }

    @Test
    fun `test refreshing user successfully with default argument`() = runBlocking {
        val user = UserDomainModel("1", "", "iii@mail.com", true)
        coEvery { mockUserRepository.refreshUser("1") } returns Resource.Success(user)

        val result = refreshUserUseCase("1")

        assertThat(result).isInstanceOf(Resource.Success::class)
        assertThat(result.data).isEqualTo(user)
    }

    @AfterEach
    fun tearDown() {
        clearMocks(mockUserRepository)
    }
}