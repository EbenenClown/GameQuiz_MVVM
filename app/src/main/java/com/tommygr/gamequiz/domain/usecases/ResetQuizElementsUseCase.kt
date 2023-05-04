package com.tommygr.gamequiz.domain.usecases

import com.tommygr.gamequiz.domain.repositories.QuizElementRepository
import javax.inject.Inject

class ResetQuizElementsUseCase @Inject constructor(private val quizElementRepository: QuizElementRepository) {
    suspend operator fun invoke() {
        val allElementsList = quizElementRepository.getAllElements().data?.shuffled()
        if (!allElementsList.isNullOrEmpty()) {
            val updatedElements = allElementsList.map { it.copy(isSolved = false, wasShown = false) }
            quizElementRepository.insertAll(updatedElements)
        }
    }
}