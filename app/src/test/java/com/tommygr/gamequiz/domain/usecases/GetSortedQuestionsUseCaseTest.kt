package com.tommygr.gamequiz.domain.usecases

import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import com.tommygr.gamequiz.domain.getQuizDomainModelListWith150Entries
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
    private lateinit var quizElementRepository: QuizElementRepository
    private lateinit var getSortedQuestionsUseCase: GetSortedQuestionsUseCase

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        getSortedQuestionsUseCase = GetSortedQuestionsUseCase(quizElementRepository)
    }

    @ParameterizedTest
    @EnumSource(GameSize::class)
    fun `invoke list, return sortedList with gamesize`(gameSize: GameSize) = runBlocking {
        val expectedList = getQuizDomainModelListWith150Entries()
        coEvery { quizElementRepository.getAllElements() } returns Resource.Success(expectedList)

        val fetchedList = getSortedQuestionsUseCase(gameSize.value).data!!

        assertThat(fetchedList).hasSize(if (gameSize == GameSize.MAX) expectedList.size else gameSize.value)
    }

    @Test
    fun `invoke empty list, return error resource with expected message`() = runBlocking() {
        coEvery { quizElementRepository.getAllElements() } returns Resource.Success(emptyList())
        val expectedMessage = "elements are empty or null"

        val fetchedResource = getSortedQuestionsUseCase(GameSize.MAX.value)

        assertThat(fetchedResource.message).isEqualTo(expectedMessage)
    }

    @AfterEach
    fun tearDown() {
        clearMocks(quizElementRepository)
    }

}