package com.tommygr.gamequiz.domain.repositories

import com.tommygr.gamequiz.domain.domainmodels.QuizElementDomainModel
import kotlinx.coroutines.flow.Flow

interface QuizElementRepository {

    suspend fun getAllElements(): List<QuizElementDomainModel>

    suspend fun getAllNotShownElements(): List<QuizElementDomainModel>

    suspend fun getAllNotSolvedElements(): List<QuizElementDomainModel>

    suspend fun refreshElements()

    suspend fun clear()
}