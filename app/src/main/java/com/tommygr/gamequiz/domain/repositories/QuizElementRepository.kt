package com.tommygr.gamequiz.domain.repositories

import com.tommygr.gamequiz.domain.domainmodels.QuizElementDomainModel
import com.tommygr.gamequiz.util.Resource
import kotlinx.coroutines.flow.Flow

interface QuizElementRepository {

    suspend fun getAllElements(): Resource<List<QuizElementDomainModel>>

    suspend fun insertAll(quizElements: List<QuizElementDomainModel>): Resource<Unit>

    suspend fun refreshElements(): Resource<List<QuizElementDomainModel>>

    suspend fun clear(): Resource<Unit>
}