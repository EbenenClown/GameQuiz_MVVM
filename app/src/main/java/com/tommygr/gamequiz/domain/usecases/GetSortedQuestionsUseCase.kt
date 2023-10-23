package com.tommygr.gamequiz.domain.usecases

import com.tommygr.gamequiz.domain.domainmodels.QuizElementDomainModel
import com.tommygr.gamequiz.domain.repositories.QuizElementRepository
import com.tommygr.gamequiz.util.Resource
import javax.inject.Inject

class GetSortedQuestionsUseCase @Inject constructor(private val quizElementRepository: QuizElementRepository) {
    suspend operator fun invoke(gameSize: Int): Resource<List<QuizElementDomainModel>> {
        val allElementsResource = quizElementRepository.getAllElements()
        val filteredList = mutableListOf<QuizElementDomainModel>()

        return if(allElementsResource is Resource.Success) {
            //room list can't be null or empty, cause it's checked in repository and results in Resource.Error -> data!! is safe
            val allElements = allElementsResource.data!!
            allElements.sortedWith(compareBy<QuizElementDomainModel>{it.wasShown}.thenBy {it.isSolved})
            filteredList += if(gameSize > 0) allElements.take(gameSize) else allElements
            Resource.Success(filteredList)
        } else {
            allElementsResource
        }
    }
}