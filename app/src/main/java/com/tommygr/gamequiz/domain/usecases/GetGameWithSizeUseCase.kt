package com.tommygr.gamequiz.domain.usecases

import com.tommygr.gamequiz.domain.domainmodels.GameDomainModel
import com.tommygr.gamequiz.domain.domainmodels.QuizElementDomainModel
import com.tommygr.gamequiz.domain.repositories.QuizElementRepository

class GetGameWithSizeUseCase(private val quizElementRepository: QuizElementRepository) {

    suspend operator fun invoke(gameSize: Int): List<QuizElementDomainModel> {
        val notShownShuffledList = quizElementRepository.getAllNotShownElements().shuffled()
        val sortedList = mutableListOf<QuizElementDomainModel>()

        sortedList.addAll(notShownShuffledList.take(gameSize))

        if(sortedList.size < gameSize) {
            val notSolvedShuffledList = quizElementRepository.getAllNotSolvedElements().shuffled()
            sortedList.addAll(notSolvedShuffledList.take(gameSize - sortedList.size))
        }

        if(sortedList.size < gameSize) {
            val allShuffledList = quizElementRepository.getAllElements().shuffled()
            sortedList.addAll(allShuffledList.take(gameSize - sortedList.size))
            val resetQuizElementsUseCase = ResetQuizElementsUseCase(quizElementRepository)
            resetQuizElementsUseCase()
        }

        return sortedList
    }
}