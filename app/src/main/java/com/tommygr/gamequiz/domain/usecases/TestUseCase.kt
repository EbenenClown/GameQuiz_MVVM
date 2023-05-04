package com.tommygr.gamequiz.domain.usecases

import com.tommygr.gamequiz.domain.domainmodels.QuizElementDomainModel
import com.tommygr.gamequiz.domain.repositories.QuizElementRepository
import javax.inject.Inject

class TestUseCase @Inject constructor(private val quizElementRepository: QuizElementRepository) {
    suspend operator fun invoke(element: QuizElementDomainModel) {
        quizElementRepository.saveElement(element)
    }
}