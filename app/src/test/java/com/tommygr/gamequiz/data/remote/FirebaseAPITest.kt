package com.tommygr.gamequiz.data.remote

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.tommygr.gamequiz.data.source.remote.FirebaseAPI
import com.tommygr.shared_test.datagenerators.quizElementDataModel
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
    private lateinit var mockFirebaseAPI: FirebaseAPI

    @BeforeEach
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testGetAllQuizElements() = runBlocking {
        val mockResponse = hashMapOf (
            "" to quizElementDataModel("1")
        )
        coEvery { mockFirebaseAPI.getAll() } returns Response.success(mockResponse)

        val response = mockFirebaseAPI.getAll().body()

        assertThat(response).isEqualTo(mockResponse)
    }

    @AfterEach
    fun tearDown() {
        clearMocks(mockFirebaseAPI)
    }
}