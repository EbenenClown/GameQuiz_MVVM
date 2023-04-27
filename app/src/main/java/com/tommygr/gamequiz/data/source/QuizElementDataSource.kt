package com.tommygr.gamequiz.data.source

import com.tommygr.gamequiz.data.source.datamodels.QuizElementDataModel
import kotlinx.coroutines.flow.Flow

interface QuizElementDataSource {
    fun observeAllElements(): Flow<List<QuizElementDataModel>>

    suspend fun getAllElements(): List<QuizElementDataModel>

    suspend fun getAllNotSolvedElements(): List<QuizElementDataModel>

    suspend fun getAllNotShownElements(): List<QuizElementDataModel>

    fun observeElement(id: String): Flow<QuizElementDataModel>

    suspend fun getElement(id:String): QuizElementDataModel

    suspend fun insertAll(quizElementDataModels: List<QuizElementDataModel>)

    suspend fun saveElement(quizElementDataModel: QuizElementDataModel)

    suspend fun updateElement(quizElementDataModel: QuizElementDataModel)

    suspend fun deleteElement(quizElementDataModel: QuizElementDataModel)

    suspend fun clear()
}