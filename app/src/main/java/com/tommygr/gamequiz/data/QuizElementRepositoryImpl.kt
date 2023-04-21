package com.tommygr.gamequiz.data

import com.tommygr.gamequiz.domain.QuizElementRepository
import com.tommygr.gamequiz.data.source.datamodels.QuizElementDataModel
import com.tommygr.gamequiz.data.source.local.localdatasources.LocalQuizElementDataSource
import com.tommygr.gamequiz.data.source.remote.remotedatasources.RemoteQuizElementDataSource
import kotlinx.coroutines.flow.Flow

class QuizElementRepositoryImpl(private val localQuizElementDataSource: LocalQuizElementDataSource
                                , private val remoteQuizElementDataSource: RemoteQuizElementDataSource):
    QuizElementRepository {

    override fun observeAllElements(): Flow<List<QuizElementDataModel>> {
        TODO("Not yet implemented")
    }

    override suspend fun getAllElements(): List<QuizElementDataModel> {
        TODO("Not yet implemented")
    }

    override suspend fun refreshElements() {
        TODO("Not yet implemented")
    }

    override fun observeElement(id: String): Flow<QuizElementDataModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getElementById(id: String): QuizElementDataModel {
        TODO("Not yet implemented")
    }

    override suspend fun refreshElement(id: String) {
        TODO("Not yet implemented")
    }

    override suspend fun saveElement(quizElementDataModel: QuizElementDataModel) {
        TODO("Not yet implemented")
    }

    override suspend fun updateElement(quizElementDataModel: QuizElementDataModel) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteElement(quizElementDataModel: QuizElementDataModel) {
        TODO("Not yet implemented")
    }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }


}