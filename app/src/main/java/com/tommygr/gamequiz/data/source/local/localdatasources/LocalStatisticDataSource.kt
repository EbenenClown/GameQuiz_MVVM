package com.tommygr.gamequiz.data.source.local.localdatasources

import com.tommygr.gamequiz.data.Statistic
import com.tommygr.gamequiz.data.source.StatisticDataSource
import com.tommygr.gamequiz.data.source.local.daos.StatisticDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalStatisticDataSource(private val statisticDao: StatisticDao, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): StatisticDataSource {
    override fun observeStatistic(userId: String)= statisticDao.observeStatistic(userId)

    override suspend fun getStatistic(userId: String) = withContext(ioDispatcher) {
        return@withContext statisticDao.getStatistic(userId)
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