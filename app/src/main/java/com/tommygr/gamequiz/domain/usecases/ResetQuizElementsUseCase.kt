package com.tommygr.gamequiz.domain.usecases

import com.tommygr.gamequiz.domain.repositories.QuizElementRepository

class ResetQuizElementsUseCase(private val quizElementRepository: QuizElementRepository) {
    suspend operator fun invoke() {
        val allElementsList = quizElementRepository.getAllElements()
        val updatedElements = allElementsList.map {
            it.copy(isSolved = false, wasShown = false)
        }
        quizElementRepository.insertAll(updatedElements)
    }
}