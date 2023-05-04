package com.tommygr.gamequiz.domain.repositories

import com.tommygr.gamequiz.domain.domainmodels.QuizElementDomainModel
import com.tommygr.gamequiz.util.Resource
import kotlinx.coroutines.flow.Flow

interface QuizElementRepository {

    suspend fun getAllElements(): Resource<List<QuizElementDomainModel>>

    suspend fun getAllNotShownElements(): Resource<List<QuizElementDomainModel>>

    suspend fun getAllNotSolvedElements(): Resource<List<QuizElementDomainModel>>

    suspend fun insertAll(quizElements: List<QuizElementDomainModel>): Resource<Unit>

    //TODO: Remove; Only for debugging user shouldn't be able to save elements
    suspend fun saveElement(quizElement: QuizElementDomainModel)

    suspend fun refreshElements(): Resource<List<QuizElementDomainModel>>

    suspend fun clear()
}