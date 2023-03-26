package com.tommygr.gamequiz.data.source

import com.tommygr.gamequiz.data.QuizElement
import kotlinx.coroutines.flow.Flow
import com.tommygr.gamequiz.data.Result

interface QuizElementDataSource {
    fun observeElements(): Flow<Result<List<QuizElement>>>

    suspend fun getElements(): Result<List<QuizElement>>

    suspend fun refreshElements()

    fun observeElement(id: String): Flow<Result<QuizElement>>

    suspend fun getElement(id:String): Result<QuizElement>

    suspend fun refreshElement(id: String)

    suspend fun saveElement(quizElement: QuizElement)

    suspend fun deleteElement(quizElement: QuizElement)

    suspend fun clear()
}