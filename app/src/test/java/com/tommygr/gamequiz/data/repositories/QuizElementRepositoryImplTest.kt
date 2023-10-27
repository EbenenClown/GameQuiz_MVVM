package com.tommygr.gamequiz.data.repositories

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.isTrue
import com.tommygr.gamequiz.data.local.quizElementDataModel
import com.tommygr.gamequiz.data.source.datamodels.QuizElementDataModel
import com.tommygr.gamequiz.data.source.datamodels.mapper.toDomainModel
import com.tommygr.gamequiz.data.source.local.daos.QuizElementDao
import com.tommygr.gamequiz.data.source.remote.remotedatasources.RemoteQuizElementDataSource
import com.tommygr.gamequiz.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.IOException
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.HttpException
import retrofit2.Response

class QuizElementRepositoryImplTest {
    @RelaxedMockK
    private lateinit var mockRemoteDataSource: RemoteQuizElementDataSource
    @RelaxedMockK
    private lateinit var mockLocalDataSource: QuizElementDao
    private lateinit var quizElementRepositoryImpl: QuizElementRepositoryImpl

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        quizElementRepositoryImpl = QuizElementRepositoryImpl(mockLocalDataSource, mockRemoteDataSource)
    }

    @Test
    fun `get All Elements, receive resource success with correct list`() = runBlocking {
        val elements = listOf(
            quizElementDataModel(id = "1", isSolved = false, wasShown = false),
            quizElementDataModel(id = "2", isSolved = false, wasShown = true),
            quizElementDataModel(id = "3", isSolved = true, wasShown = true)
        )
        val expectedList = elements.toDomainModel()

        coEvery { mockLocalDataSource.getAllQuizElements() } returns elements

        val retrievedResourceQuizElements = quizElementRepositoryImpl.getAllElements()
        assertThat(retrievedResourceQuizElements).isInstanceOf(Resource.Success::class.java)
        assertThat(retrievedResourceQuizElements.data!!).isEqualTo(expectedList)
    }

    @Test
    fun `get empty List, receive resource error with correct message`() = runBlocking {
        coEvery { mockLocalDataSource.getAllQuizElements() } returns emptyList()

        val retrievedResourceQuizElements = quizElementRepositoryImpl.getAllElements()

        assertThat(retrievedResourceQuizElements).isInstanceOf(Resource.Error::class.java)
        assertThat(retrievedResourceQuizElements.message).isEqualTo("quizElements are empty")
    }

    @Test
    fun `refresh elements, receive resource success with correct list`() = runBlocking {
        val elementsFirebaseResponse = hashMapOf(
            "firebaseId1" to quizElementDataModel(id = "1", isSolved = false, wasShown = false),
            "firebaseId2" to quizElementDataModel(id = "2", isSolved = false, wasShown = true),
            "firebaseId3" to quizElementDataModel(id = "3", isSolved = true, wasShown = true)
        )
        val elements = listOf(
            quizElementDataModel(id = "1", isSolved = false, wasShown = false),
            quizElementDataModel(id = "2", isSolved = false, wasShown = true),
            quizElementDataModel(id = "3", isSolved = true, wasShown = true)
        )
        val responseHashMap: Response<HashMap<String, QuizElementDataModel>> = Response.success(elementsFirebaseResponse)
        coEvery { mockRemoteDataSource.getAllElements() } returns responseHashMap

        val refreshedElements = quizElementRepositoryImpl.getRemoteQuizElements()

        //Same order doesn't matter, because they are going to be shuffled anyway
        coVerify { mockLocalDataSource.insertAll(match { it.containsAll(elements) }) }
        assertThat(refreshedElements).isInstanceOf(Resource.Success::class.java)
        assertThat(refreshedElements.data!!.containsAll(elements.toDomainModel())).isTrue()
    }

    @Test
    fun `refresh element response body is null , receive resource error with correct message`() = runBlocking {
        val responseHashMap: Response<HashMap<String, QuizElementDataModel>> = Response.success(null)
        coEvery { mockRemoteDataSource.getAllElements() } returns responseHashMap

        val refreshedElements = quizElementRepositoryImpl.getRemoteQuizElements()

        assertThat(refreshedElements).isInstanceOf(Resource.Error::class)
        assertThat(refreshedElements.message).isEqualTo("quizElementList is null")
    }

    @Test
    fun `refresh element throws retrofit exception, receive resource error with correct message`() = runBlocking {
        coEvery { mockRemoteDataSource.getAllElements() } throws HttpException(Response.error<ResponseBody>(404 , "".toResponseBody()))

        val refreshedElements = quizElementRepositoryImpl.getRemoteQuizElements()

        assertThat(refreshedElements).isInstanceOf(Resource.Error::class)
        assertThat(refreshedElements.message).isEqualTo("retrofit2.HttpException: HTTP 404 Response.error()")
    }

    @Test
    fun `update Element, get resource Success`() = runBlocking {
        val quizElement = quizElementDataModel("1")
        coEvery { mockLocalDataSource.updateQuizElement(quizElement) } returns Unit

        val response = quizElementRepositoryImpl.updateElement(quizElement)

        assertThat(response).isInstanceOf(Resource.Success::class)
        assertThat(response.data).isEqualTo(Unit)
    }

    @Test
    fun `update Element throws exception, get resource Error`() = runBlocking {
        val quizElement = quizElementDataModel("1")
        coEvery { mockLocalDataSource.updateQuizElement(quizElement) } throws IOException()

        val response = quizElementRepositoryImpl.updateElement(quizElement)

        assertThat(response).isInstanceOf(Resource.Error::class)
        assertThat(response.message).isEqualTo("java.io.IOException")
    }

    @Test
    fun `clear table, get resource success`() = runBlocking {
        coEvery { mockLocalDataSource.clear() } returns Unit

        val response = quizElementRepositoryImpl.clear()

        assertThat(response).isInstanceOf(Resource.Success::class)
        assertThat(response.data).isEqualTo(Unit)
    }

    @Test
    fun `clear table throws exception, get resource error`() = runBlocking {
        coEvery { mockLocalDataSource.clear() } throws IOException()

        val response = quizElementRepositoryImpl.clear()
        assertThat(response).isInstanceOf(Resource.Error::class)
        assertThat(response.message).isEqualTo("java.io.IOException")
    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }
}