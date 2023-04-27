package com.tommygr.gamequiz.data.source.remote.remotedatasources

import com.tommygr.gamequiz.data.source.datamodels.QuizElementDataModel
import com.tommygr.gamequiz.data.source.QuizElementDataSource
import com.tommygr.gamequiz.data.source.remote.FirebaseAPI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RemoteQuizElementDataSource(private val firebaseAPI: FirebaseAPI, private val dispatcher: CoroutineDispatcher = Dispatchers.IO): QuizElementDataSource {
    override fun observeAllElements(): Flow<List<QuizElementDataModel>> = flow { emit(firebaseAPI.getAll()) }

    override suspend fun getAllElements(): List<QuizElementDataModel> = withContext(dispatcher) {
        return@withContext firebaseAPI.getAll()
    }

    override suspend fun getAllNotSolvedElements(): List<QuizElementDataModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllNotShownElements(): List<QuizElementDataModel> {
        TODO("Not yet implemented")
    }

    override fun observeElement(id: String): Flow<QuizElementDataModel> = flow { emit(firebaseAPI.getElementById(id)) }

    override suspend fun getElement(id: String): QuizElementDataModel = withContext(dispatcher) {
        return@withContext firebaseAPI.getElementById(id)
    }

    override suspend fun insertAll(quizElementDataModels: List<QuizElementDataModel>) {
        firebaseAPI.saveELementList(quizElementDataModels)
    }

    override suspend fun saveElement(quizElementDataModel: QuizElementDataModel)  = withContext(dispatcher) {
        firebaseAPI.saveElement(quizElementDataModel)
    }

    override suspend fun updateElement(quizElementDataModel: QuizElementDataModel)  = withContext(dispatcher) {
        firebaseAPI.updateElement(quizElementDataModel.id, quizElementDataModel)
    }

    override suspend fun deleteElement(quizElementDataModel: QuizElementDataModel)  = withContext(dispatcher) {
        firebaseAPI.deleteElement(quizElementDataModel.id)
    }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }

}