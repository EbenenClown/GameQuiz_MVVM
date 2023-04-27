package com.tommygr.gamequiz.data.repositories

import com.tommygr.gamequiz.domain.repositories.QuizElementRepository
import com.tommygr.gamequiz.data.source.local.localdatasources.LocalQuizElementDataSource
import com.tommygr.gamequiz.data.source.remote.remotedatasources.RemoteQuizElementDataSource
import com.tommygr.gamequiz.domain.domainmodels.QuizElementDomainModel
import com.tommygr.gamequiz.data.source.datamodels.mapper.toDomainModel
import kotlinx.coroutines.flow.map

class QuizElementRepositoryImpl(private val localQuizElementDataSource: LocalQuizElementDataSource
                                , private val remoteQuizElementDataSource: RemoteQuizElementDataSource):
    QuizElementRepository {

    override fun observeAllElements() = localQuizElementDataSource.observeAllElements().map { it.toDomainModel() }

    override suspend fun getAllElements() = localQuizElementDataSource.getAllElements().toDomainModel()
    override suspend fun getAllNotShownElements(): List<QuizElementDomainModel> = localQuizElementDataSource.getAllNotShownElements().toDomainModel()

    override suspend fun getAllNotSolvedElements(): List<QuizElementDomainModel> = localQuizElementDataSource.getAllNotSolvedElements().toDomainModel()

    override suspend fun refreshElements() {
        val elements = remoteQuizElementDataSource.getAllElements()
        localQuizElementDataSource.insertAll(elements)
    }

    override fun observeElement(id: String) = localQuizElementDataSource.observeElement(id).map { it.toDomainModel() }

    override suspend fun getElementById(id: String) = localQuizElementDataSource.getElement(id).toDomainModel()

    override suspend fun saveElement(quizElementDomainModel: QuizElementDomainModel) {
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