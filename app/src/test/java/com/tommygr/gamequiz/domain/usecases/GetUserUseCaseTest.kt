package com.tommygr.gamequiz.domain.usecases

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.prop
import com.tommygr.gamequiz.domain.domainmodels.UserDomainModel
import com.tommygr.gamequiz.domain.repositories.UserRepository
import com.tommygr.gamequiz.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetUserUseCaseTest {
    @MockK
    private lateinit var mockUserRepository: UserRepository
    private lateinit var getUserUseCase: GetUserUseCase

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        getUserUseCase = GetUserUseCase(mockUserRepository)
    }

    @Test
    fun `test getting user successfully with default argument`() = runBlocking {
        val user = UserDomainModel("1" ,"", "iii@mail.com", true)
        coEvery { mockUserRepository.getUser() } returns Resource.Success(user)

        val result = getUserUseCase()

        assertThat(result).isInstanceOf(Resource.Success::class)
            .prop("User") { Resource.Success<UserDomainModel>::data.call(it) }.isEqualTo(user)


        coVerify { mockUserRepository.getUser() }
    }

    @AfterEach
    fun tearDown() {
        clearMocks(mockUserRepository)
    }
}