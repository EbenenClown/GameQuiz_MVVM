package com.tommygr.gamequiz.data.source.local.localdatasources

import com.tommygr.gamequiz.data.Result
import com.tommygr.gamequiz.data.Statistic
import com.tommygr.gamequiz.data.source.StatisticDataSource
import com.tommygr.gamequiz.data.source.local.daos.StatisticDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.lang.Exception

class LocalStatisticDataSource(private val statisticDao: StatisticDao, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): StatisticDataSource {
    override fun observeStatistic(userId: String): Flow<Result<Statistic>> {
        return statisticDao.observeStatistic(userId).map { Result.Success(it) }
    }

    override suspend fun getStatistic(userId: String) = withContext(ioDispatcher) {
        return@withContext try {
            Result.Success(statisticDao.getStatistic(userId))
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    override suspend fun refreshStatistic() {
        TODO("Not yet implemented")
    }

    override suspend fun saveStatistic(statistic: Statistic) = withContext(ioDispatcher) {
        statisticDao.insertStatistic(statistic)
    }

    override suspend fun updateStatistic(statistic: Statistic) = withContext(ioDispatcher) {
        statisticDao.updateStatistic(statistic)
    }

    override suspend fun deleteStatisticWithId(userId: String) = withContext(ioDispatcher) {
        statisticDao.deleteStatistic(userId)
    }


    override suspend fun clear() = withContext(ioDispatcher) {
        statisticDao.clear()
    }

}