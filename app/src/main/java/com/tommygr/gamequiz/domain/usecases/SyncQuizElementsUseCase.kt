package com.tommygr.gamequiz.domain.usecases

import com.tommygr.gamequiz.domain.domainmodels.QuizElementDomainModel
import com.tommygr.gamequiz.domain.repositories.QuizElementRepository
import com.tommygr.gamequiz.util.Resource
import javax.inject.Inject

class SyncQuizElementsUseCase @Inject constructor(private val quizElementRepository: QuizElementRepository) {
    suspend operator fun invoke(): Resource<List<QuizElementDomainModel>> {
        val remoteDataResult = quizElementRepository.getRemoteQuizElements()
        val localDataResult = quizElementRepository.getAllElements()

        if (remoteDataResult is Resource.Success && localDataResult is Resource.Success) {
            val retrievedRemoteData = remoteDataResult.data
            val retrievedLocalData = localDataResult.data

            if (!retrievedRemoteData.isNullOrEmpty() && !retrievedLocalData.isNullOrEmpty()) {
                val localElementsById = retrievedLocalData.associateBy { it.id }
                val updatedLocalData = retrievedRemoteData.map { remoteElement ->
                    localElementsById[remoteElement.id]?.takeIf { it.wasShown } ?: remoteElement
                }
                quizElementRepository.insertAll(updatedLocalData)
                return Resource.Success(updatedLocalData)
            }
        }
        return Resource.Error("Data is null")
    }
}
