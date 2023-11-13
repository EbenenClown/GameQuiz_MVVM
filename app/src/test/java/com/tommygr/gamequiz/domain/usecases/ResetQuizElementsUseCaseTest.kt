package com.tommygr.gamequiz.domain.usecases

import com.tommygr.gamequiz.domain.repositories.QuizElementRepository
import com.tommygr.shared_test.datagenerators.quizElementDomainModel
import io.mockk.MockKAnnotations
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ResetQuizElementsUseCaseTest {
    @RelaxedMockK
    private lateinit var mockQuizElementRepository: QuizElementRepository
    private lateinit var resetQuizElementsUseCase: ResetQuizElementsUseCase

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        resetQuizElementsUseCase = ResetQuizElementsUseCase(mockQuizElementRepository)
    }

    @Test
    fun `reset list with random wasShown and isSolved values, expect all wasShown and isSolved are false`() =
        runBlocking {
            val generatedList = listOf(
                quizElementDomainModel(
                    "1",
                    isSolved = true,
                    wasShown = true
                ),
                quizElementDomainModel(
                    "2",
                    isSolved = true,
                    wasShown = false
                ),
                quizElementDomainModel(
                    "3",
                    isSolved = false,
                    wasShown = true
                )
            )
            coEvery { mockQuizElementRepository.getAllElements().data } returns generatedList

            resetQuizElementsUseCase()

            coVerify { mockQuizElementRepository.insertAll(match { it.all { element -> !element.isSolved && !element.wasShown } }) }
        }

    @Test
    fun `give empty list, expect list won't be saved`() = runBlocking {
        coEvery { mockQuizElementRepository.getAllElements().data } returns emptyList()

        resetQuizElementsUseCase()

        coVerify(exactly = 0) { mockQuizElementRepository.insertAll(any()) }
    }

    @AfterEach
    fun tearDown() {
        clearMocks(mockQuizElementRepository)
    }
}