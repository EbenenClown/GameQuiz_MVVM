package com.tommygr.gamequiz.data.repositories

import android.util.Log
import com.tommygr.gamequiz.data.source.datamodels.QuizElementDataModel
import com.tommygr.gamequiz.data.source.datamodels.mapper.toDataModel
import com.tommygr.gamequiz.domain.repositories.QuizElementRepository
import com.tommygr.gamequiz.data.source.remote.remotedatasources.RemoteQuizElementDataSource
import com.tommygr.gamequiz.domain.domainmodels.QuizElementDomainModel
import com.tommygr.gamequiz.data.source.datamodels.mapper.toDomainModel
import com.tommygr.gamequiz.data.source.local.daos.QuizElementDao
import com.tommygr.gamequiz.util.Resource
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.math.log

class QuizElementRepositoryImpl @Inject constructor(private val localDataSource: QuizElementDao
                                , private val remoteDataSource: RemoteQuizElementDataSource):
    QuizElementRepository {

    override suspend fun getAllElements(): Resource<List<QuizElementDomainModel>> {
        return try {
            Resource.Success(localDataSource.getAllQuizElements().map { it.toDomainModel() })
        } catch (e: Exception) {
            Resource.Error(e.toString())
        }
    }

    override suspend fun refreshElements(): Resource<List<QuizElementDomainModel>> {
        return try {
            val elementBody = remoteDataSource.getAllElements().body()
            elementBody?.values?.let { mutableList ->
                val immutableList = mutableList.toList()
                localDataSource.insertAll(immutableList)
                Resource.Success(immutableList.map { it.toDomainModel() })
            }
            Resource.Error("quizElementList is null")
        } catch (e: Exception) {
            Resource.Error(e.toString())
        }
    }

    override suspend fun insertAll(quizElements: List<QuizElementDomainModel>): Resource<Unit> {
        return try {
            localDataSource.insertAll(quizElements.toDataModel())
            Resource.Success(Unit)
        } catch (e: Exception) {
            Resource.Error(e.toString())
        }
    }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }
}