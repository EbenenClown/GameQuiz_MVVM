package com.tommygr.gamequiz.data.source.local.localdatasources

import com.tommygr.gamequiz.data.source.datamodels.QuizElementDataModel
import com.tommygr.gamequiz.data.source.QuizElementDataSource
import com.tommygr.gamequiz.data.source.local.database.QuizElementDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalQuizElementDataSource(private val quizElementDao: QuizElementDao, private val dispatcher: CoroutineDispatcher = Dispatchers.IO): QuizElementDataSource {
    override fun observeAllElements() = quizElementDao.observeAllQuizElements()

    override suspend fun getAllElements(): List<QuizElementDataModel> = withContext(dispatcher) {
        return@withContext quizElementDao.getAllQuizElements()

    }

    override suspend fun getAllNotSolvedElements(): List<QuizElementDataModel> = withContext(dispatcher) {
        return@withContext quizElementDao.getAllNotSolvedElements()
    }

    override suspend fun getAllNotShownElements(): List<QuizElementDataModel> = withContext(dispatcher) {
        return@withContext quizElementDao.getAllNotShownElements()

    }

    override fun observeElement(id: String) = quizElementDao.observeQuizElement(id)

    override suspend fun getElement(id: String) = withContext(dispatcher) {
        return@withContext quizElementDao.getQuizElement(id)

    }

    override suspend fun insertAll(quizElementDataModels: List<QuizElementDataModel>) = withContext(dispatcher) {
        quizElementDao.insertAll(quizElementDataModels)
    }

    override suspend fun saveElement(quizElementDataModel: QuizElementDataModel) = withContext(dispatcher) {
        quizElementDao.insertQuizElement(quizElementDataModel)
    }

    override suspend fun updateElement(quizElementDataModel: QuizElementDataModel) = withContext(dispatcher) {
        quizElementDao.updateQuizElement(quizElementDataModel)
    }

    override suspend fun deleteElement(quizElementDataModel: QuizElementDataModel) = withContext(dispatcher) {
        quizElementDao.deleteQuizElement(quizElementDataModel)
    }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }

}