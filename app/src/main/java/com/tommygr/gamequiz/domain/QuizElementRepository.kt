package com.tommygr.gamequiz.domain

import com.tommygr.gamequiz.data.source.datamodels.QuizElementDataModel
import kotlinx.coroutines.flow.Flow

interface QuizElementRepository {
    fun observeAllElements(): Flow<List<QuizElementDataModel>>

    suspend fun getAllElements(): List<QuizElementDataModel>

    suspend fun refreshElements()

    fun observeElement(id: String): Flow<QuizElementDataModel>

    suspend fun getElementById(id: String): QuizElementDataModel

    suspend fun refreshElement(id: String)

    suspend fun saveElement(quizElementDataModel: QuizElementDataModel)

    suspend fun updateElement(quizElementDataModel: QuizElementDataModel)

    suspend fun deleteElement(quizElementDataModel: QuizElementDataModel)

    suspend fun clear()
}