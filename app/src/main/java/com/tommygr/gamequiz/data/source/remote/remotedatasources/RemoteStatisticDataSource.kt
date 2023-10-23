package com.tommygr.gamequiz.data.source.remote.remotedatasources

import com.tommygr.gamequiz.data.source.datamodels.StatisticDataModel
import com.tommygr.gamequiz.data.source.remote.FirebaseAPI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import javax.inject.Inject

class RemoteStatisticDataSource @Inject constructor(private val firebaseAPI: FirebaseAPI, private val dispatcher: CoroutineDispatcher = Dispatchers.IO) {

     suspend fun getStatistic(userId: String): Response<HashMap<String, StatisticDataModel>> = withContext(dispatcher) {
        return@withContext firebaseAPI.getStatisticById(userId)
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
}