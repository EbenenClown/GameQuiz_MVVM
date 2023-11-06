package com.tommygr.gamequiz.ui.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tommygr.gamequiz.domain.repositories.DataStoreRepository
import com.tommygr.gamequiz.domain.usecases.CreateLocalUserUseCase
import com.tommygr.gamequiz.domain.usecases.GetUserUseCase
import com.tommygr.gamequiz.domain.usecases.RefreshUserUseCase
import com.tommygr.gamequiz.domain.usecases.SyncQuizElementsUseCase
import com.tommygr.gamequiz.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class MainScreenUiState {
    data object IsLoading: MainScreenUiState()
    data class Success(val userName: String, val isLoggedIn: Boolean, val errorMessage: String?): MainScreenUiState()
    data class Failure(val errorMessage: String?): MainScreenUiState()
}

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val refreshUserUseCase: RefreshUserUseCase,
    private val createLocalUserUseCase: CreateLocalUserUseCase,
    private val syncQuizElementsUseCase: SyncQuizElementsUseCase,
    private val dataStoreRepository: DataStoreRepository,
) : ViewModel() {
    private val _uiState = MutableStateFlow<MainScreenUiState>(MainScreenUiState.IsLoading)
    val uiState = _uiState.asStateFlow()

    fun getUserNameAndCheckForLoggedInStatus() {
        viewModelScope.launch(Dispatchers.IO) {
            val userResource = getUserUseCase()
            //When Resource is success there is data, it's checked in the repository -> !! is safe
            if (userResource is Resource.Success && userResource.data!!.userId.isNotEmpty()) {
                _uiState.value = MainScreenUiState.Success(
                    userName = userResource.data.userName,
                    isLoggedIn = false,
                    errorMessage = null
                )
                if (userResource.data.isRegistered) {
                    isUserLoggedIn()
                }
            } else {
                createLocalUserUseCase()
            }
        }
    }

    fun syncQuizElements() {
        viewModelScope.launch(Dispatchers.IO) {
            syncQuizElementsUseCase()
        }
    }

    fun navigateToScreen() {

    }

    private fun isUserLoggedIn() {
        viewModelScope.launch(Dispatchers.IO) {
            dataStoreRepository.getUserId().collect {
                if (it.isNotEmpty()) {
                    when (val resource = refreshUserUseCase(it)){
                        is Resource.Success -> {
                            //When Resource is success there is data, it's checked in the repository -> !! is safe
                            _uiState.value = MainScreenUiState.Success(
                                userName = resource.data!!.userName,
                                isLoggedIn = true,
                                errorMessage = null
                            )
                        }
                        is Resource.Error -> {
                            _uiState.value = MainScreenUiState.Failure (
                                errorMessage = resource.message
                            )
                        }
                    }
                }
            }
        }
    }
}