package com.tommygr.gamequiz.data.remote

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.tommygr.gamequiz.data.local.quizElementDataModel
import com.tommygr.gamequiz.data.source.remote.FirebaseAPI
import io.mockk.MockKAnnotations
import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Response

class FirebaseAPITest {

    @RelaxedMockK
    private lateinit var firebaseAPI: FirebaseAPI

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testGetAllQuizElements() = runBlocking {
        val mockResponse = hashMapOf (
            "" to quizElementDataModel("1")
        )
        coEvery { firebaseAPI.getAll() } returns Response.success(mockResponse)

        val response = firebaseAPI.getAll().body()

        assertThat(response).isEqualTo(mockResponse)
    }

    @Test
    fun testSaveQuizElement() = runBlocking {
        coEvery { firebaseAPI.saveElement(any()) } returns Response.success(null)

        val response = firebaseAPI.saveElement(quizElementDataModel("1"))

        assertThat(response).isEqualTo(Response.success(null))
    }

    @AfterEach
    fun tearDown() {
        clearMocks(firebaseAPI)
    }
}