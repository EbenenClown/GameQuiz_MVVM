package com.tommygr.gamequiz.domain.usecases

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.tommygr.gamequiz.domain.repositories.QuizElementRepository
import com.tommygr.gamequiz.util.Resource
import com.tommygr.gamequiz.util.dataGenerators.quizElementDomainModel
import io.mockk.MockKAnnotations
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class SyncQuizElementsUseCaseTest {
    @RelaxedMockK
    private lateinit var mockQuizRepository: QuizElementRepository
    private lateinit var syncQuizElementsUseCase: SyncQuizElementsUseCase

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
        syncQuizElementsUseCase = SyncQuizElementsUseCase(mockQuizRepository)
    }

    @Test
    fun `give two identical lists, get resource success with local list`() = runBlocking {
        val fakeRemoteDataList = listOf (
            quizElementDomainModel("1"),
            quizElementDomainModel("2"),
            quizElementDomainModel("3")
            )
        val fakeLocalDataList = listOf (
            quizElementDomainModel("1"),
            quizElementDomainModel("2"),
            quizElementDomainModel("3")
        )
        coEvery { mockQuizRepository.getRemoteQuizElements() } returns Resource.Success(fakeRemoteDataList)
        coEvery { mockQuizRepository.getAllElements() } returns Resource.Success(fakeLocalDataList)

        val retrievedResponse = syncQuizElementsUseCase()

        assertThat(retrievedResponse).isInstanceOf(Resource.Success::class)
        assertThat(retrievedResponse.data).isEqualTo(fakeLocalDataList)
    }

    @Test
    fun `give longer remote data list, get resource success with local list is equal to remote list`() = runBlocking {
        val fakeRemoteDataList = listOf (
            quizElementDomainModel("1"),
            quizElementDomainModel("2"),
            quizElementDomainModel("3"),
            quizElementDomainModel("4"),
            quizElementDomainModel("5")
        )
        val fakeLocalDataList = listOf (
            quizElementDomainModel("1"),
            quizElementDomainModel("2"),
            quizElementDomainModel("3")
        )
        coEvery { mockQuizRepository.getRemoteQuizElements() } returns Resource.Success(fakeRemoteDataList)
        coEvery { mockQuizRepository.getAllElements() } returns Resource.Success(fakeLocalDataList)

        val retrievedResponse = syncQuizElementsUseCase()

        coVerify { mockQuizRepository.clear() }
        coVerify { mockQuizRepository.insertAll(match { it == fakeRemoteDataList}) }
        assertThat(retrievedResponse).isInstanceOf(Resource.Success::class)
        assertThat(retrievedResponse.data).isEqualTo(fakeRemoteDataList)
    }

    @Test
    fun `give longer remote list and local list with different parameter, get resource success with mixed list`() = runBlocking {
        val fakeRemoteDataList = listOf (
            quizElementDomainModel("1"),
            quizElementDomainModel("2"),
            quizElementDomainModel("3"),
            quizElementDomainModel("4"),
            quizElementDomainModel("5")
        )
        val fakeLocalDataList = listOf (
            quizElementDomainModel("1"),
            quizElementDomainModel("2", wasShown = true),
            quizElementDomainModel("3", isSolved = true, wasShown = true)
        )
        val expectedList = listOf (
            quizElementDomainModel("1"),
            quizElementDomainModel("2", wasShown = true),
            quizElementDomainModel("3", isSolved = true, wasShown = true),
            quizElementDomainModel("4"),
            quizElementDomainModel("5")
        )
        coEvery { mockQuizRepository.getRemoteQuizElements() } returns Resource.Success(fakeRemoteDataList)
        coEvery { mockQuizRepository.getAllElements() } returns Resource.Success(fakeLocalDataList)

        val retrievedResponse = syncQuizElementsUseCase()

        coVerify { mockQuizRepository.clear() }
        coVerify { mockQuizRepository.insertAll(match { it == expectedList}) }
        assertThat(retrievedResponse).isInstanceOf(Resource.Success::class)
        assertThat(retrievedResponse.data).isEqualTo(expectedList)
    }

    @Test
    fun `give remote response failure, get resource error with correct message`() = runBlocking {
        val fakeRemoteDataList = listOf (
            quizElementDomainModel("1"),
            quizElementDomainModel("2"),
            quizElementDomainModel("3")
        )

        coEvery { mockQuizRepository.getRemoteQuizElements() } returns Resource.Success(fakeRemoteDataList)
        coEvery { mockQuizRepository.getAllElements() } returns Resource.Error("Data is null")

        val retrievedResponse = syncQuizElementsUseCase()

        assertThat(retrievedResponse).isInstanceOf(Resource.Error::class)
        assertThat(retrievedResponse.message).isEqualTo("Couldn't sync quizelements")

    }

    @AfterEach
    fun tearDown() {
        clearMocks(mockQuizRepository)
    }
}