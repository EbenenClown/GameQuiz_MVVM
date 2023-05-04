package com.tommygr.gamequiz.data.repositories

import android.util.Log
import com.tommygr.gamequiz.data.source.datamodels.QuizElementDataModel
import com.tommygr.gamequiz.data.source.datamodels.mapper.toDataModel
import com.tommygr.gamequiz.domain.repositories.QuizElementRepository
import com.tommygr.gamequiz.data.source.remote.remotedatasources.RemoteQuizElementDataSource
import com.tommygr.gamequiz.domain.domainmodels.QuizElementDomainModel
import com.tommygr.gamequiz.data.source.datamodels.mapper.toDomainModel
import com.tommygr.gamequiz.data.source.local.daos.QuizElementDao
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.math.log

class QuizElementRepositoryImpl @Inject constructor(private val localDataSource: QuizElementDao
                                , private val remoteDataSource: RemoteQuizElementDataSource):
    QuizElementRepository {

    override suspend fun getAllElements() = remoteDataSource.getAllElements().toDomainModel()
    override suspend fun getAllNotShownElements(): List<QuizElementDomainModel> = localDataSource.getAllNotShownElements().toDomainModel()

    override suspend fun getAllNotSolvedElements(): List<QuizElementDomainModel> = localDataSource.getAllNotSolvedElements().toDomainModel()

    override suspend fun refreshElements() {
        val elements = remoteDataSource.getAllElements()
        localDataSource.insertAll(elements)
    }

    override suspend fun insertAll(quizElements: List<QuizElementDomainModel>) {
        localDataSource.insertAll(quizElements.toDataModel())
    }

    override suspend fun saveElement(quizElement: QuizElementDomainModel) {
        try {
            remoteDataSource.saveElement(QuizElementDataModel(quizElement.id, quizElement.type, quizElement.question, quizElement.options, quizElement.difficulty, quizElement.hint, quizElement.isSolved, quizElement.wasShown ))
        } catch (e: Exception) {
            Log.d("test123", "saveElement: ${e.message}")
        }


    }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }


}