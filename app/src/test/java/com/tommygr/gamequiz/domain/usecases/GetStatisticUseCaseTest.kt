package com.tommygr.gamequiz.domain.usecases

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.tommygr.gamequiz.domain.domainmodels.StatisticDomainModel
import com.tommygr.gamequiz.domain.repositories.StatisticRepository
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

class GetStatisticUseCaseTest {
    @MockK
    lateinit var mockStatisticRepository: StatisticRepository
    lateinit var getStatisticUseCase: GetStatisticUseCase

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        getStatisticUseCase = GetStatisticUseCase(mockStatisticRepository)
    }

    @Test
    fun `test getting statistic successfully`() = runBlocking {
        val statisticDomainModel = StatisticDomainModel("1", 1, 1, 1)
        coEvery { mockStatisticRepository.getStatistic("1") } returns Resource.Success(statisticDomainModel)

        val result = getStatisticUseCase("1",)

        assertThat(result).isInstanceOf(Resource.Success::class.java)
        assertThat(result.data).isEqualTo(statisticDomainModel)

        coVerify { mockStatisticRepository.getStatistic("1") }
    }

    @AfterEach
    fun tearDown() {
        clearMocks(mockStatisticRepository)
    }
}