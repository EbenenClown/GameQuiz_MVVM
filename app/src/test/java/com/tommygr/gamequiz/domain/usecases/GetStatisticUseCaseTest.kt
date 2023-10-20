package com.tommygr.gamequiz.domain.usecases

import assertk.assertThat
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
    lateinit var statisticRepository: StatisticRepository
    lateinit var getStatisticUseCase: GetStatisticUseCase

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        getStatisticUseCase = GetStatisticUseCase(statisticRepository)
    }

    @Test
    fun `test getting statistic successfully`() = runBlocking {
        val statisticDomainModel = StatisticDomainModel("1", 1, 1, 1)
        coEvery { statisticRepository.getStatistic(any()) } returns Resource.Success(statisticDomainModel)

        val result = getStatisticUseCase(forceUpdate = false)

        assertThat(result is Resource.Success)
        assertThat((result as Resource.Success).data == statisticDomainModel)

        coVerify { statisticRepository.getStatistic("",false) }
    }

    @Test
    fun `test getting statistic with force update`() = runBlocking {
        val statisticDomainModel = StatisticDomainModel("1", 1, 1, 1)
        coEvery { statisticRepository.getStatistic(any()) } returns Resource.Success(statisticDomainModel)

        val result = getStatisticUseCase(forceUpdate = true)

        assertThat(result is Resource.Success)
        assertThat((result as Resource.Success).data == statisticDomainModel)

        coVerify { statisticRepository.getStatistic("1",true) }
    }

    @AfterEach
    fun tearDown() {
        clearMocks(statisticRepository)
    }
}