package com.tommygr.gamequiz.data.local.daos

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isNull
import com.tommygr.gamequiz.data.source.datamodels.StatisticDataModel
import com.tommygr.gamequiz.data.source.local.GameQuizDb
import com.tommygr.gamequiz.data.source.local.daos.StatisticDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class StatisticDaoTest() {
    private lateinit var database: GameQuizDb
    private lateinit var statisticDao: StatisticDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GameQuizDb::class.java
        ).allowMainThreadQueries().build()

        statisticDao = database.statisticDao()
    }

    @Test
    fun insertStatistic_retrieveInFlow() = runTest {
        val statistic = StatisticDataModel("1",3,2,1)
        statisticDao.insertNewStatistic(statistic)

        statisticDao.observeStatistic("1").test {
            val statisticFlow = awaitItem()
            assertThat(statisticFlow).isEqualTo(statistic)
            cancel()
        }
    }

    @Test
    fun updateStatisticAfterSaving_flowEmitsUpdatedData() = runTest {
        val statistic = StatisticDataModel("1",3,2,1)
        statisticDao.insertNewStatistic(statistic)

        val updatedStatistic = statistic.copy(gamesWon = 5)

        statisticDao.observeStatistic("1").test {
            assertThat(awaitItem()).isEqualTo(statistic)

            launch(Dispatchers.Default) {
                delay(100)
                statisticDao.insertNewStatistic(updatedStatistic)
            }

            assertThat(awaitItem()).isEqualTo(updatedStatistic)
            cancel()
        }
    }

    @Test
    fun insertStatistic_retrieveCorrectStatistic() {
        val statistic = StatisticDataModel("1",3,2,1)
        statisticDao.insertNewStatistic(statistic)

        val retrievedStatistic = statisticDao.getStatistic("1")
        assertThat(retrievedStatistic).isEqualTo(statistic)
    }

    @Test
    fun updateStatistic_correctUpdatedValues() {
        val statistic = StatisticDataModel("1",3,2,1)
        statisticDao.insertNewStatistic(statistic)

        val newStatistic = StatisticDataModel("1",4,3,2)
        statisticDao.updateStatistic(newStatistic)

        val retrievedStatistic = statisticDao.getStatistic("1")
        assertThat(retrievedStatistic).isEqualTo(newStatistic)
    }

    @Test
    fun deleteStatistic_getNull() {
        val statistic = StatisticDataModel("1",3,2,1)
        statisticDao.insertNewStatistic(statistic)

        statisticDao.deleteStatistic("1")

        val retrievedStatistic = statisticDao.getStatistic("1")
        assertThat(retrievedStatistic).isNull()
    }

    @Test
    fun clearTable_getNull() {
        val statistic = StatisticDataModel("1",3,2,1)
        statisticDao.insertNewStatistic(statistic)

        statisticDao.clear()

        val retrievedStatistic = statisticDao.getStatistic("1")
        assertThat(retrievedStatistic).isNull()
    }

    @After
    fun tearDown() {
        database.close()
    }
}