package com.tommygr.gamequiz.data.source.local.localdatasources

import com.tommygr.gamequiz.data.QuizElement
import com.tommygr.gamequiz.data.Result
import com.tommygr.gamequiz.data.source.QuizElementDataSource
import com.tommygr.gamequiz.data.source.local.daos.QuizElementDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class LocalQuizElementDataSource(private val quizElementDao: QuizElementDao, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): QuizElementDataSource {
    override fun observeAllElements(): Flow<Result<List<QuizElement>>> {
        return quizElementDao.observeAllQuizElements().map {
            Result.Success(it)
        }
    }

    override suspend fun getAllElements(): Result<List<QuizElement>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(quizElementDao.getAllQuizElements())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override fun observeScrambledElements(): Flow<Result<List<QuizElement>>> {
        return quizElementDao.observeScrambledQuizElements().map {
            Result.Success(it)
        }
    }

    override suspend fun getScrambledElements(): Result<List<QuizElement>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(quizElementDao.getScrambledQuizElements())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override fun observePictureElements(): Flow<Result<List<QuizElement>>> {
        return quizElementDao.observePictureQuizElements().map {
            Result.Success(it)
        }
    }

    override suspend fun getPictureElements(): Result<List<QuizElement>> = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(quizElementDao.getPictureQuizElements())
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun refreshElements() {
        TODO("Not yet implemented")
    }

    override fun observeElement(id: String): Flow<Result<QuizElement>> {
        return quizElementDao.observeQuizElement(id).map {
            Result.Success(it)
        }
    }

    override suspend fun getElement(id: String) = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(quizElementDao.getQuizElement(id))
        } catch (e: Exception) {
            Result.Error(e)
        }
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