package com.tommygr.gamequiz.domain.usecases

import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import com.tommygr.gamequiz.domain.repositories.QuizElementRepository
import com.tommygr.gamequiz.util.GameSize
import com.tommygr.gamequiz.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class GetSortedQuestionsUseCaseTest {
    @RelaxedMockK
    private lateinit var mockQuizElementRepository: QuizElementRepository
    private lateinit var getSortedQuestionsUseCase: GetSortedQuestionsUseCase

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        getSortedQuestionsUseCase = GetSortedQuestionsUseCase(mockQuizElementRepository)
    }

    @ParameterizedTest
    @EnumSource(GameSize::class)
    fun `sort list with gamesize enumsource, return sortedList with gamesize`(gameSize: GameSize) =
        runBlocking {
            val expectedList =
                com.tommygr.gamequiz.util.dataGenerators.getQuizDomainModelListWith150Entries()
            coEvery { mockQuizElementRepository.getAllElements() } returns Resource.Success(
                expectedList
            )

            val fetchedList = getSortedQuestionsUseCase(gameSize.value).data!!

            assertThat(fetchedList).hasSize(if (gameSize == GameSize.MAX) expectedList.size else gameSize.value)
        }

    @ParameterizedTest
    @EnumSource(GameSize::class)
    fun `sort list returns error, error resource with expected message`(gameSize: GameSize) =
        runBlocking() {
            coEvery { mockQuizElementRepository.getAllElements() } returns Resource.Error("elements are empty or null")
            val expectedMessage = "elements are empty or null"

            val fetchedResource = getSortedQuestionsUseCase(gameSize.value)

            assertThat(fetchedResource.message).isEqualTo(expectedMessage)
        }

    @AfterEach
    fun tearDown() {
        clearMocks(mockQuizElementRepository)
    }

}