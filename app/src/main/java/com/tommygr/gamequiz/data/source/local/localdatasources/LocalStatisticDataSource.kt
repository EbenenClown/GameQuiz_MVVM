package com.tommygr.gamequiz.data.source.local.localdatasources

import com.tommygr.gamequiz.data.source.datamodels.StatisticDataModel
import com.tommygr.gamequiz.data.source.StatisticDataSource
import com.tommygr.gamequiz.data.source.local.database.StatisticDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LocalStatisticDataSource(private val statisticDao: StatisticDao, private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO): StatisticDataSource {
    override fun observeStatistic(userId: String)= statisticDao.observeStatistic(userId)

    override suspend fun getStatistic(userId: String) = withContext(ioDispatcher) {
        return@withContext statisticDao.getStatistic(userId)
    }

    override suspend fun saveStatistic(statisticDataModel: StatisticDataModel) = withContext(ioDispatcher) {
        statisticDao.insertStatistic(statisticDataModel)
    }

    override suspend fun updateStatistic(statisticDataModel: StatisticDataModel) = withContext(ioDispatcher) {
        statisticDao.updateStatistic(statisticDataModel)
    }

    override suspend fun deleteStatisticWithId(userId: String) = withContext(ioDispatcher) {
        statisticDao.deleteStatistic(userId)
    }


    override suspend fun clear() = withContext(ioDispatcher) {
        statisticDao.clear()
    }

}