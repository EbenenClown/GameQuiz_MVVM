package com.tommygr.gamequiz.data.source.remote.remotedatasources

import com.tommygr.gamequiz.data.Statistic
import com.tommygr.gamequiz.data.source.StatisticDataSource
import com.tommygr.gamequiz.data.source.remote.FirebaseAPI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class RemoteStatisticDataSource(private val firebaseAPI: FirebaseAPI, private val dispatcher: CoroutineDispatcher = Dispatchers.IO): StatisticDataSource {
    override fun observeStatistic(userId: String): Flow<Statistic> = flow { emit(firebaseAPI.getStatisticById(userId)) }

    override suspend fun getStatistic(userId: String): Statistic = withContext(dispatcher) {
        return@withContext firebaseAPI.getStatisticById(userId)
    }

    override suspend fun refreshStatistic() {
        TODO("Not yet implemented")
    }

    override suspend fun saveStatistic(statistic: Statistic) = withContext(dispatcher) {
        return@withContext firebaseAPI.saveStatistic(statistic)
    }

    override suspend fun updateStatistic(statistic: Statistic) = withContext(dispatcher) {
        return@withContext firebaseAPI.updateStatistic(statistic.userId, statistic)
    }

    override suspend fun deleteStatisticWithId(userId: String) = withContext(dispatcher) {
        return@withContext firebaseAPI.deleteStatistic(userId)
    }

    override suspend fun clear() {
        TODO("Not yet implemented")
    }

}