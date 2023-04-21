package com.tommygr.gamequiz.data.source.remote.remotedatasources

import com.tommygr.gamequiz.data.QuizElement
import com.tommygr.gamequiz.data.source.QuizElementDataSource
import com.tommygr.gamequiz.data.source.remote.FirebaseAPI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RemoteQuizElementDataSource(private val firebaseAPI: FirebaseAPI, private val dispatcher: CoroutineDispatcher = Dispatchers.IO): QuizElementDataSource {
    override fun observeAllElements(): Flow<List<QuizElement>> = flow { emit(firebaseAPI.getAll()) }

    override suspend fun getAllElements(): List<QuizElement> = withContext(dispatcher) {
        return@withContext firebaseAPI.getAll()
    }

    override suspend fun refreshElements() {
        TODO("Not yet implemented")
    }

    override fun observeElement(id: String): Flow<QuizElement> = flow { emit(firebaseAPI.getElementById(id)) }

    override suspend fun getElement(id: String): QuizElement = withContext(dispatcher) {
        return@withContext firebaseAPI.getElementById(id)
    }

    override suspend fun refreshElement(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveElement(quizElement: QuizElement)  = withContext(dispatcher) {
        return@withContext firebaseAPI.saveElement(quizElement)
    }

    override suspend fun updateElement(quizElement: QuizElement)  = withContext(dispatcher) {
        return@withContext firebaseAPI.updateElement(quizElement.id, quizElement)
    }

    override suspend fun deleteElement(quizElement: QuizElement)  = withContext(dispatcher) {
        return@withContext firebaseAPI.deleteElement(quizElement.id)
    }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }

}