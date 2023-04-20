package com.tommygr.gamequiz.data.source

import com.tommygr.gamequiz.data.QuizElement
import kotlinx.coroutines.flow.Flow

interface QuizElementDataSource {
    fun observeAllElements(): Flow<List<QuizElement>>

    suspend fun getAllElements(): List<QuizElement>

    fun observeScrambledElements(): Flow<List<QuizElement>>

    suspend fun getScrambledElements(): List<QuizElement>

    fun observePictureElements(): Flow<List<QuizElement>>

    suspend fun getPictureElements(): List<QuizElement>

    suspend fun refreshElements()

    fun observeElement(id: String): Flow<QuizElement>

    suspend fun getElement(id:String): QuizElement

    suspend fun refreshElement(id: String)

    suspend fun saveElement(quizElement: QuizElement)

    suspend fun updateElement(quizElement: QuizElement)

    suspend fun deleteElement(quizElement: QuizElement)

    suspend fun clear()
}