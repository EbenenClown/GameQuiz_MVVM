package com.tommygr.gamequiz.ui.viewmodels

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.tommygr.gamequiz.domain.domainmodels.UserDomainModel
import com.tommygr.gamequiz.domain.usecases.CreateLocalUserUseCase
import com.tommygr.gamequiz.domain.usecases.GetUserUseCase
import com.tommygr.gamequiz.domain.usecases.RefreshUserUseCase
import com.tommygr.gamequiz.domain.usecases.SyncQuizElementsUseCase
import com.tommygr.gamequiz.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainScreenViewModelTest {
    @RelaxedMockK
    private lateinit var mockGetUserUseCase: GetUserUseCase

    @RelaxedMockK
    private lateinit var mockRefreshUserUseCase: RefreshUserUseCase

    @RelaxedMockK
    private lateinit var mockCreateLocalUserUseCase: CreateLocalUserUseCase

    @RelaxedMockK
    private lateinit var mockSyncQuizElementsUseCase: SyncQuizElementsUseCase

    private lateinit var mainScreenViewModel: MainScreenViewModel


    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(UnconfinedTestDispatcher())
        mainScreenViewModel = MainScreenViewModel(
            mockGetUserUseCase,
            mockRefreshUserUseCase,
            mockCreateLocalUserUseCase,
            mockSyncQuizElementsUseCase,
        )
    }

    @Test
    fun `get not registered user and check for name and logged in status, loading toggles and local user is loaded`() = runTest{
        val mockUser = UserDomainModel("1", "", "", false)
        coEvery { mockGetUserUseCase() } returns Resource.Success(mockUser)

        assertThat(mainScreenViewModel.uiState.value).isEqualTo(MainScreenUiState.IsLoading)
        mainScreenViewModel.getUserNameAndCheckForLoggedInStatus()

        mainScreenViewModel.uiState.test {
            assertThat(awaitItem()).isEqualTo(MainScreenUiState.Success("", false, null))
        }
    }

    @Test
    fun `get registered user and check for name and logged in status, loading toggles and user is loaded and logged in`() = runTest{
        val mockUser = UserDomainModel("1", "Test", "Test@mail.com", true)
        coEvery { mockGetUserUseCase() } returns Resource.Success(mockUser)
        coEvery { mockRefreshUserUseCase() } returns Resource.Success(mockUser)

        assertThat(mainScreenViewModel.uiState.value).isEqualTo(MainScreenUiState.IsLoading)
        mainScreenViewModel.getUserNameAndCheckForLoggedInStatus()

        mainScreenViewModel.uiState.test {
            assertThat(awaitItem()).isEqualTo(MainScreenUiState.Success("Test", true, null))
        }
    }

    @Test
    fun `get registered user and check for name and logged in status, loading toggles and user is loaded but not logged in`() = runTest {
        val mockUser = UserDomainModel("1", "Test", "Test@mail.com", true)
        coEvery { mockGetUserUseCase() } returns Resource.Success(mockUser)
        coEvery { mockRefreshUserUseCase() } returns Resource.Error("user login failed")

        assertThat(mainScreenViewModel.uiState.value).isEqualTo(MainScreenUiState.IsLoading)
        mainScreenViewModel.getUserNameAndCheckForLoggedInStatus()

        mainScreenViewModel.uiState.test {
            assertThat(awaitItem()).isEqualTo(MainScreenUiState.Failure("user login failed"))
        }
    }

    @Test
    fun `get empty user and check for name and logged in status, loading toggles and new local user is created`() = runTest {
        val mockUser = UserDomainModel("", "", "", false)
        coEvery { mockGetUserUseCase() } returns Resource.Success(mockUser)

        assertThat(mainScreenViewModel.uiState.value).isEqualTo(MainScreenUiState.IsLoading)
        mainScreenViewModel.getUserNameAndCheckForLoggedInStatus()

        coVerify { mockCreateLocalUserUseCase(any()) }
    }

    @Test
    fun `sync quiz elements, use case is called`() = runTest {
        mainScreenViewModel.syncQuizElements()

        coVerify { mockSyncQuizElementsUseCase() }
    }

    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }
}