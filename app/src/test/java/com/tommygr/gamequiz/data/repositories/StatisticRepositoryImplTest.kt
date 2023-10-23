package com.tommygr.gamequiz.data.repositories

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import com.tommygr.gamequiz.data.source.datamodels.StatisticDataModel
import com.tommygr.gamequiz.data.source.datamodels.mapper.toDataModel
import com.tommygr.gamequiz.data.source.datamodels.mapper.toDomainModel
import com.tommygr.gamequiz.data.source.local.daos.StatisticDao
import com.tommygr.gamequiz.data.source.remote.remotedatasources.RemoteStatisticDataSource
import com.tommygr.gamequiz.domain.domainmodels.StatisticDomainModel
import com.tommygr.gamequiz.util.Resource
import io.mockk.MockKAnnotations
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import okio.IOException
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import retrofit2.Response

class StatisticRepositoryImplTest {
    @RelaxedMockK
    private lateinit var mockLocalDataSource: StatisticDao
    @RelaxedMockK
    private lateinit var mockRemoteDataSource: RemoteStatisticDataSource
    private lateinit var statisticRepositoryImpl: StatisticRepositoryImpl

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)
        statisticRepositoryImpl = StatisticRepositoryImpl(mockLocalDataSource, mockRemoteDataSource)
    }

    @Test
    fun `get statistic, retrieve resource success with correct list`() = runBlocking {
        val statistic = StatisticDataModel("1", 1 , 1, 1)
        coEvery { mockLocalDataSource.getStatistic("1") } returns statistic

        val receivedStatistic = statisticRepositoryImpl.getStatistic("1")

        assertThat(receivedStatistic).isInstanceOf(Resource.Success::class.java)
        assertThat(receivedStatistic.data).isEqualTo(statistic.toDomainModel())
    }

    @Test
    fun `get statistic throws exception, retrieve resource error with correct message`() = runBlocking {
        coEvery { mockLocalDataSource.getStatistic("1") } throws IOException()

        val receivedStatistic = statisticRepositoryImpl.getStatistic("1")

        assertThat(receivedStatistic).isInstanceOf(Resource.Error::class.java)
        assertThat(receivedStatistic.message).isEqualTo("java.io.IOException")
    }

    @Test
    fun `refresh statistic, receive resource success with correct statistic`() = runBlocking {
        val statisticFirebaseResponse = hashMapOf(
            "firebaseId1" to StatisticDataModel("1", 1, 1, 1)
        )
        val statistic = StatisticDataModel("1", 1, 1, 1)
        val responseHashMap: Response<HashMap<String, StatisticDataModel>> = Response.success(statisticFirebaseResponse)
        coEvery { mockRemoteDataSource.getStatistic("1") } returns responseHashMap

        val refreshedElements = statisticRepositoryImpl.refreshStatistic("1")

        coVerify { mockLocalDataSource.insertNewStatistic(match { it == statistic }) }
        assertThat(refreshedElements).isInstanceOf(Resource.Success::class.java)
        assertThat(refreshedElements.data!!).isEqualTo(statistic.toDomainModel())
    }

    @Test
    fun `refresh statistic response body is null, receive resource error with correct message`() = runBlocking {
        val responseHashMap: Response<HashMap<String, StatisticDataModel>> = Response.success(null)
        coEvery { mockRemoteDataSource.getStatistic(any()) } returns responseHashMap

        val refreshedElements = statisticRepositoryImpl.refreshStatistic("1")

        assertThat(refreshedElements).isInstanceOf(Resource.Error::class.java)
        assertThat(refreshedElements.message!!).isEqualTo("statistic is null")
    }

    @Test
    fun `refresh statistic throws exception, receive resource error with correct message`() = runBlocking {
        coEvery { mockRemoteDataSource.getStatistic(any()) } throws IOException()

        val refreshedElements = statisticRepositoryImpl.refreshStatistic("1")

        assertThat(refreshedElements).isInstanceOf(Resource.Error::class.java)
        assertThat(refreshedElements.message!!).isEqualTo("java.io.IOException")
    }

    @Test
    fun `add new statistic, receive resource success with unit`() = runBlocking{
        val statistic = StatisticDomainModel("1", 1, 1, 1)
        coEvery { mockLocalDataSource.insertNewStatistic(statistic.toDataModel()) } returns Unit

        val response = statisticRepositoryImpl.addNewStatistic(statistic)

        assertThat(response).isInstanceOf(Resource.Success::class)
        assertThat(response.data).isEqualTo(Unit)
    }

    @Test
    fun `add new statistic throws exception, receive resource error with correct message`() = runBlocking{
        val statistic = StatisticDomainModel("1", 1, 1, 1)
        coEvery { mockLocalDataSource.insertNewStatistic(statistic.toDataModel()) } throws IOException()

        val response = statisticRepositoryImpl.addNewStatistic(statistic)

        assertThat(response).isInstanceOf(Resource.Error::class)
        assertThat(response.message).isEqualTo("java.io.IOException")
    }

    @Test
    fun `update statistic, receive resource success with unit`() = runBlocking{
        val statistic = StatisticDomainModel("1", 1, 1, 1)
        coEvery { mockLocalDataSource.updateStatistic(statistic.toDataModel()) } returns Unit

        val response = statisticRepositoryImpl.updateStatistic(statistic)

        assertThat(response).isInstanceOf(Resource.Success::class)
        assertThat(response.data).isEqualTo(Unit)
    }

    @Test
    fun `update statistic throws exception, receive resource error with correct message`() = runBlocking{
        val statistic = StatisticDomainModel("1", 1, 1, 1)
        coEvery { mockLocalDataSource.updateStatistic(statistic.toDataModel()) } throws IOException()

        val response = statisticRepositoryImpl.updateStatistic(statistic)

        assertThat(response).isInstanceOf(Resource.Error::class)
        assertThat(response.message).isEqualTo("java.io.IOException")
    }

    @Test
    fun `delete statistic, receive resource success with unit`() = runBlocking{
        coEvery { mockLocalDataSource.deleteStatistic("1") } returns Unit

        val response = statisticRepositoryImpl.deleteStatistic("1")

        assertThat(response).isInstanceOf(Resource.Success::class)
        assertThat(response.data).isEqualTo(Unit)
    }

    @Test
    fun `delete statistic throws exception, receive resource error with correct message`() = runBlocking{
        coEvery { mockLocalDataSource.deleteStatistic("1") } throws IOException()

        val response = statisticRepositoryImpl.deleteStatistic("1")

        assertThat(response).isInstanceOf(Resource.Error::class)
        assertThat(response.message).isEqualTo("java.io.IOException")
    }


    @AfterEach
    fun tearDown() {
        clearAllMocks()
    }
}