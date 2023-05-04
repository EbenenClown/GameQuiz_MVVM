package com.tommygr.gamequiz.data.source.remote.remotedatasources

import com.tommygr.gamequiz.data.source.datamodels.StatisticDataModel
import com.tommygr.gamequiz.data.source.remote.FirebaseAPI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteStatisticDataSource @Inject constructor(private val firebaseAPI: FirebaseAPI, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {
     fun observeStatistic(userId: String): Flow<StatisticDataModel> = flow { emit(firebaseAPI.getStatisticById(userId).values.first()) }

     suspend fun getStatistic(userId: String): StatisticDataModel = withContext(dispatcher) {
        return@withContext firebaseAPI.getStatisticById(userId).values.first()
    }

     suspend fun addNewStatistic(statisticDataModel: StatisticDataModel) = withContext(dispatcher) {
        return@withContext firebaseAPI.saveStatistic(statisticDataModel)
    }

     suspend fun updateStatistic(statisticDataModel: StatisticDataModel) = withContext(dispatcher) {
        return@withContext firebaseAPI.updateStatistic(statisticDataModel.userId, statisticDataModel)
    }

     suspend fun deleteStatisticWithId(userId: String) = withContext(dispatcher) {
        return@withContext firebaseAPI.deleteStatistic(userId)
    }

     suspend fun clear() {
        TODO("Not yet implemented")
    }

}