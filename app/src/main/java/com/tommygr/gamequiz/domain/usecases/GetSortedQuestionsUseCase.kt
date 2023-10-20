package com.tommygr.gamequiz.domain.usecases

import com.tommygr.gamequiz.domain.domainmodels.QuizElementDomainModel
import com.tommygr.gamequiz.domain.repositories.QuizElementRepository
import com.tommygr.gamequiz.util.Resource
import javax.inject.Inject

class GetSortedQuestionsUseCase @Inject constructor(private val quizElementRepository: QuizElementRepository) {
    suspend operator fun invoke(gameSize: Int): Resource<List<QuizElementDomainModel>> {
        val allElements = quizElementRepository.getAllElements().data?.shuffled()
        val filteredList = mutableListOf<QuizElementDomainModel>()

        if(!allElements.isNullOrEmpty()) {
            allElements.sortedWith(compareBy<QuizElementDomainModel>{it.wasShown}.thenBy {it.isSolved})
            filteredList += if(gameSize > 0) allElements.take(gameSize) else allElements
        } else {
            return Resource.Error("elements are empty or null")
        }

        return Resource.Success(filteredList)
    }
}