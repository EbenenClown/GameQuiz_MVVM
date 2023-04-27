package com.tommygr.gamequiz.domain.repositories

import com.tommygr.gamequiz.domain.domainmodels.QuizElementDomainModel
import kotlinx.coroutines.flow.Flow

interface QuizElementRepository {
    fun observeAllElements(): Flow<List<QuizElementDomainModel>>

    suspend fun getAllElements(): List<QuizElementDomainModel>

    suspend fun getAllNotShownElements(): List<QuizElementDomainModel>

    suspend fun getAllNotSolvedElements(): List<QuizElementDomainModel>

    suspend fun refreshElements()

    fun observeElement(id: String): Flow<QuizElementDomainModel>

    suspend fun getElementById(id: String): QuizElementDomainModel

    suspend fun saveElement(quizElementDomainModel: QuizElementDomainModel)

    suspend fun insertAll(quizElementDomainModelList: List<QuizElementDomainModel>)

    suspend fun updateElement(quizElementDomainModel: QuizElementDomainModel)

    suspend fun deleteElement(quizElementDomainModel: QuizElementDomainModel)

    suspend fun clear()
}