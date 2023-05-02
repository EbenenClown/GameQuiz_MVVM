package com.tommygr.gamequiz.data.repositories

import com.tommygr.gamequiz.domain.repositories.QuizElementRepository
import com.tommygr.gamequiz.data.source.remote.remotedatasources.RemoteQuizElementDataSource
import com.tommygr.gamequiz.domain.domainmodels.QuizElementDomainModel
import com.tommygr.gamequiz.data.source.datamodels.mapper.toDomainModel
import com.tommygr.gamequiz.data.source.local.database.QuizElementDao
import kotlinx.coroutines.flow.map

class QuizElementRepositoryImpl(private val localDataSource: QuizElementDao
                                , private val remoteDataSource: RemoteQuizElementDataSource):
    QuizElementRepository {

    override fun observeAllElements() = localDataSource.observeAllQuizElements().map { it.toDomainModel() }

    override suspend fun getAllElements() = localDataSource.getAllQuizElements().toDomainModel()
    override suspend fun getAllNotShownElements(): List<QuizElementDomainModel> = localDataSource.getAllNotShownElements().toDomainModel()

    override suspend fun getAllNotSolvedElements(): List<QuizElementDomainModel> = localDataSource.getAllNotSolvedElements().toDomainModel()

    override suspend fun refreshElements() {
        val elements = remoteDataSource.getAllElements()
        localDataSource.insertAll(elements)
    }

    override fun observeElement(id: String) = localDataSource.observeQuizElement(id).map { it.toDomainModel() }

    override suspend fun getElementById(id: String) = localDataSource.getQuizElement(id).toDomainModel()

    override suspend fun saveElement(quizElementDomainModel: QuizElementDomainModel) {
        TODO("Not yet implemented")
    }

    override suspend fun insertAll(quizElementDomainModelList: List<QuizElementDomainModel>) {
        TODO("Not yet implemented")
    }

    override suspend fun updateElement(quizElementDomainModel: QuizElementDomainModel) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteElement(quizElementDomainModel: QuizElementDomainModel) {
        TODO("Not yet implemented")
    }


    override suspend fun clear() {
        TODO("Not yet implemented")
    }


}