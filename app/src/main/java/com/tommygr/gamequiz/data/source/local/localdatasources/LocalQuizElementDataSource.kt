package com.tommygr.gamequiz.data.source.local.localdatasources

import com.tommygr.gamequiz.data.QuizElement
import com.tommygr.gamequiz.data.source.QuizElementDataSource
import com.tommygr.gamequiz.data.source.local.daos.QuizElementDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalQuizElementDataSource(private val quizElementDao: QuizElementDao, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): QuizElementDataSource {
    override fun observeAllElements() = quizElementDao.observeAllQuizElements()

    override suspend fun getAllElements(): List<QuizElement> = withContext(ioDispatcher) {
        return@withContext quizElementDao.getAllQuizElements()

    }

    override fun observeScrambledElements() = quizElementDao.observeScrambledQuizElements()

    override suspend fun getScrambledElements(): List<QuizElement> = withContext(ioDispatcher) {
        return@withContext quizElementDao.getScrambledQuizElements()

    }

    override fun observePictureElements() = quizElementDao.observePictureQuizElements()

    override suspend fun getPictureElements(): List<QuizElement> = withContext(ioDispatcher) {
        return@withContext quizElementDao.getPictureQuizElements()
    }

    override suspend fun refreshElements() {
        TODO("Not yet implemented")
    }

    override fun observeElement(id: String) = quizElementDao.observeQuizElement(id)

    override suspend fun getElement(id: String) = withContext(ioDispatcher) {
        return@withContext quizElementDao.getQuizElement(id)

    }

    override suspend fun refreshElement(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveElement(quizElement: QuizElement) = withContext(ioDispatcher) {
        quizElementDao.insertQuizElement(quizElement)
    }

    override suspend fun updateElement(quizElement: QuizElement) = withContext(ioDispatcher) {
        quizElementDao.updateQuizElement(quizElement)
    }

    override suspend fun deleteElement(quizElement: QuizElement) = withContext(ioDispatcher) {
        quizElementDao.deleteQuizElement(quizElement)
    }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }

}