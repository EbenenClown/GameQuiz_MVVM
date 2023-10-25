package com.tommygr.gamequiz.ui.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tommygr.gamequiz.domain.usecases.GetUserUseCase
import com.tommygr.gamequiz.util.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


data class MainScreenUiState (
    val userName: String = ""
)

class MainScreenViewModel @Inject constructor(private val getUserUseCase: GetUserUseCase): ViewModel() {
    private val _uiState = MutableStateFlow(MainScreenUiState())
    val uiState = _uiState.asStateFlow()

     fun getUserName() {
        viewModelScope.launch {
            val userResource = getUserUseCase()
            if(userResource is Resource.Success) {
                _uiState.update {
                    it.copy(userName = userResource.data?.userName ?: "")
                }
            }
        }
    }
}