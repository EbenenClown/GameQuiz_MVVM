package com.tommygr.gamequiz.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tommygr.gamequiz.domain.domainmodels.QuizElementDomainModel
import com.tommygr.gamequiz.domain.repositories.QuizElementRepository
import com.tommygr.gamequiz.domain.usecases.TestUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(private val quizElementRepository: QuizElementRepository): ViewModel() {
    private var _elements = MutableStateFlow<List<QuizElementDomainModel>>(emptyList())
    val elements get() = _elements.asStateFlow()

    init {
        viewModelScope.launch {
            _elements.emit(quizElementRepository.getAllElements())
        }
    }
}